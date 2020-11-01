package com.tanay.githubrepoviewer.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import com.tanay.githubrepoviewer.data.models.local.GithubRepo
import javax.inject.Inject

interface IGithubRepoDao {

    suspend fun insert(items: List<GithubRepo>)

    fun repos(): DataSource.Factory<Int, GithubRepo>
}

class GithubRepoDao @Inject constructor(val internalDao: GithubRepoInternalDao) : IGithubRepoDao {
    override suspend fun insert(items: List<GithubRepo>) = internalDao.insert(items)

    override fun repos(): DataSource.Factory<Int, GithubRepo> = internalDao.repos()
}