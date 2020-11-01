package com.tanay.githubrepoviewer.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanay.githubrepoviewer.data.models.local.GithubRepo

@Dao
interface GithubRepoInternalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<GithubRepo>)

    @Query("select * from GithubRepo order by id")
    fun repos(): DataSource.Factory<Int, GithubRepo>
}