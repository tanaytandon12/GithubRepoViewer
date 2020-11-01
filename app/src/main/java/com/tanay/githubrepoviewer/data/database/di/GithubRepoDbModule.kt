package com.tanay.githubrepoviewer.data.database.di

import com.tanay.githubrepoviewer.data.database.GithubRepoDao
import com.tanay.githubrepoviewer.data.database.IGithubRepoDao
import com.tanay.githubrepoviewer.di.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class GithubRepoDbModule {

    @Provides
    @AppScope
    fun githubRepoDao(dao: GithubRepoDao): IGithubRepoDao = dao

}