package com.tanay.githubrepoviewer

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
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

fun Any.launchUrl(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}