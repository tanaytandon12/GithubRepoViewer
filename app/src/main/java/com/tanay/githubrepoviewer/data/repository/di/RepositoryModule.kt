package com.tanay.githubrepoviewer.data.repository.di

import com.tanay.githubrepoviewer.data.repository.GithubRepoRepository
import com.tanay.githubrepoviewer.data.repository.IGithubRepoRepository
import com.tanay.githubrepoviewer.di.AppScope
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    @AppScope
    fun githubRepoRepository(repo: GithubRepoRepository): IGithubRepoRepository = repo
}