package com.tanay.githubrepoviewer.usecase.di

import com.tanay.githubrepoviewer.di.AppScope
import com.tanay.githubrepoviewer.usecase.GithubRepoListUseCase
import com.tanay.githubrepoviewer.usecase.IGithubRepoListUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    @AppScope
    fun repoListUseCase(useCase: GithubRepoListUseCase): IGithubRepoListUseCase = useCase
}