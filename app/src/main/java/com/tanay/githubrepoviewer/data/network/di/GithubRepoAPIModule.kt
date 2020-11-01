package com.tanay.githubrepoviewer.data.network.di

import com.tanay.githubrepoviewer.data.models.network.GithubRepoResponse
import com.tanay.githubrepoviewer.data.network.GithubRepoAPI
import com.tanay.githubrepoviewer.data.network.IGithubRepoAPI
import com.tanay.githubrepoviewer.di.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object GithubRepoAPIModule {

    @Provides
    @AppScope
    fun githubRepoAPI(githubRepoAPI: GithubRepoAPI): IGithubRepoAPI = githubRepoAPI
}