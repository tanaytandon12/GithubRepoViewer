package com.tanay.githubrepoviewer.di

import android.app.Application
import android.content.Context
import com.tanay.githubrepoviewer.data.database.di.GithubRepoDbModule
import com.tanay.githubrepoviewer.data.network.di.GithubRepoAPIModule
import com.tanay.githubrepoviewer.data.repository.di.RepositoryModule
import com.tanay.githubrepoviewer.usecase.di.UseCaseModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [GithubRepoAPIModule::class, GithubRepoDbModule::class,
        RepositoryModule::class, UseCaseModule::class]
)
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun context(): Context = application
}