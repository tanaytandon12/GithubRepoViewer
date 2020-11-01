package com.tanay.githubrepoviewer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tanay.githubrepoviewer.data.models.local.GithubRepo

@Database(entities = [GithubRepo::class], version = 1, exportSchema = false)
abstract class GithubRepoDatabase : RoomDatabase() {
    companion object {
        const val NAME = "GithubRepoDatabase"
    }

    abstract fun repoDao(): GithubRepoInternalDao
}