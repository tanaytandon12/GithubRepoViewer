package com.tanay.githubrepoviewer.data.database.di

import android.content.Context
import androidx.room.Room
import com.tanay.githubrepoviewer.data.database.GithubRepoDao
import com.tanay.githubrepoviewer.data.database.GithubRepoDatabase
import com.tanay.githubrepoviewer.data.database.GithubRepoInternalDao
import com.tanay.githubrepoviewer.data.database.IGithubRepoDao
import com.tanay.githubrepoviewer.di.AppScope
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    @AppScope
    fun githubRepoDatabase(context: Context): GithubRepoDatabase = Room.databaseBuilder(
        context, GithubRepoDatabase::class.java, GithubRepoDatabase.NAME
    ).build()

    @Provides
    @AppScope
    fun githubRepoInternalDao(database: GithubRepoDatabase): GithubRepoInternalDao =
        database.repoDao()

}