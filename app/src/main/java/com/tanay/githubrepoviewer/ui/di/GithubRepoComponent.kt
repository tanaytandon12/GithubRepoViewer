package com.tanay.githubrepoviewer.ui.di

import com.tanay.githubrepoviewer.ui.repo_list.RepoListFragment
import dagger.Subcomponent

@Subcomponent(modules = [GithubRepoModule::class])
interface GithubRepoComponent {

    fun inject(repoListFragment: RepoListFragment)
}