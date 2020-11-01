package com.tanay.githubrepoviewer.ui.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.tanay.githubrepoviewer.ui.repo_list.IRepoListViewModel
import com.tanay.githubrepoviewer.ui.repo_list.RepoListViewModel
import dagger.Module
import dagger.Provides

@Module
class GithubRepoModule(private val fragmentActivity: FragmentActivity) {

    @Provides
    fun repoListViewModel(factory: RepoListViewModel.Factory): IRepoListViewModel =
        ViewModelProvider(fragmentActivity, factory)[RepoListViewModel::class.java]
}