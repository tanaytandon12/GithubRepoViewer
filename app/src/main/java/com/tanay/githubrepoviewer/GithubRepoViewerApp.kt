package com.tanay.githubrepoviewer

import android.app.Application
import androidx.fragment.app.Fragment
import com.tanay.githubrepoviewer.di.AppComponent
import com.tanay.githubrepoviewer.di.AppModule
import com.tanay.githubrepoviewer.di.DaggerAppComponent

class GithubRepoViewerApp : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}

val Fragment.appComponent by lazy {
    GithubRepoViewerApp.component
}