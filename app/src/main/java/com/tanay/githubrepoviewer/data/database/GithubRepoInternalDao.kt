package com.tanay.githubrepoviewer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tanay.githubrepoviewer.data.models.local.GithubRepo

@Dao
interface GithubRepoInternalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<GithubRepo>)
}