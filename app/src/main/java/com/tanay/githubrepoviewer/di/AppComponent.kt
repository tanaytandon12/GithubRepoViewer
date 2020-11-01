package com.tanay.githubrepoviewer.di

import com.tanay.githubrepoviewer.data.database.di.GithubRepoDbModule
import com.tanay.githubrepoviewer.data.network.di.GithubRepoAPIModule
import com.tanay.githubrepoviewer.data.repository.di.RepositoryModule
import com.tanay.githubrepoviewer.ui.di.GithubRepoComponent
import com.tanay.githubrepoviewer.ui.di.GithubRepoModule
import com.tanay.githubrepoviewer.usecase.di.UseCaseModule
import dagger.Component
import javax.inject.Scope

@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun repoListComponent(module: GithubRepoModule): GithubRepoComponent
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
