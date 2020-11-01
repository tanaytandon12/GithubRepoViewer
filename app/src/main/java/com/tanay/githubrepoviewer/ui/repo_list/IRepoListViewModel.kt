package com.tanay.githubrepoviewer.ui.repo_list

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tanay.githubrepoviewer.data.models.RepoListState
import com.tanay.githubrepoviewer.data.models.local.GithubRepo
import com.tanay.githubrepoviewer.usecase.IGithubRepoListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

interface IRepoListViewModel {

    fun repoList(): LiveData<PagedList<GithubRepo>>

    fun repoListState(): LiveData<RepoListState>
}

class RepoListViewModel(
    private val mIGithubRepoListUseCase: IGithubRepoListUseCase,
    private val io: CoroutineDispatcher = Dispatchers.IO,
    private val main: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(),
    IRepoListViewModel {


    companion object {
        private const val PAGE_SIZE = 10
    }

    private val _mRepoLiveDataInternal: LiveData<PagedList<GithubRepo>> by lazy {
        LivePagedListBuilder(
            mIGithubRepoListUseCase.repos(),
            PagedList.Config.Builder().setPageSize(PAGE_SIZE).setEnablePlaceholders(false).build()
        ).setBoundaryCallback(object : PagedList.BoundaryCallback<GithubRepo>() {
            override fun onItemAtEndLoaded(itemAtEnd: GithubRepo) {
                fetchData()
            }

            override fun onZeroItemsLoaded() {
                fetchData()
            }
        }).build()
    }

    private val _mRepoListStateLiveData: MutableLiveData<RepoListState> by lazy {
        MutableLiveData<RepoListState>(RepoListState.Init)
    }

    private val _exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        conveyFailure()
    }

    override fun repoList(): LiveData<PagedList<GithubRepo>> = _mRepoLiveDataInternal

    override fun repoListState(): LiveData<RepoListState> = _mRepoListStateLiveData

    private fun fetchData() {
        _mRepoListStateLiveData.postValue(RepoListState.Loading)
        viewModelScope.launch(io + _exceptionHandler) {
            val result = mIGithubRepoListUseCase.fetchRepos(PAGE_SIZE)
            if (result is RepoListState.Failure) {
                conveyFailure(result.errorMessage)
            } else {
                conveySuccess(result)
            }
        }
    }

    private fun conveyFailure(errorMessage: String? = null) {
        viewModelScope.launch(main) {
            _mRepoListStateLiveData.postValue(RepoListState.Failure(errorMessage))
        }
    }

    private fun conveySuccess(state: RepoListState) {
        viewModelScope.launch(main) {
            _mRepoListStateLiveData.postValue(state)
        }
    }

    class Factory @Inject constructor(private val mIGithubRepoListUseCase: IGithubRepoListUseCase) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(RepoListViewModel::class.java)) {
                RepoListViewModel(
                    mIGithubRepoListUseCase
                ) as T
            } else {
                super.create(modelClass)
            }
        }
    }
}