package com.tanay.githubrepoviewer.usecase

import androidx.paging.DataSource
import com.tanay.githubrepoviewer.data.models.RepoListState
import com.tanay.githubrepoviewer.data.models.RepoResult
import com.tanay.githubrepoviewer.data.models.local.GithubRepo
import com.tanay.githubrepoviewer.data.repository.IGithubRepoRepository
import javax.inject.Inject

interface IGithubRepoListUseCase {

    fun repos(): DataSource.Factory<Int, GithubRepo>

    suspend fun fetchRepos(pageSize: Int): RepoListState
}

class GithubRepoListUseCase @Inject constructor(private val mGithubRepository: IGithubRepoRepository) :
    IGithubRepoListUseCase {
    override suspend fun fetchRepos(pageSize: Int): RepoListState {
        return when (val result = mGithubRepository.fetchRepos(pageSize)) {
            is RepoResult.Success -> {
                if (result.data?.limitReached == true) {
                    RepoListState.LimitReached
                } else {
                    RepoListState.Fetched
                }
            }
            is RepoResult.Failure -> {
                RepoListState.Failure(result.errorMessage)
            }
        }
    }

    override fun repos(): DataSource.Factory<Int, GithubRepo> = mGithubRepository.repos()

}