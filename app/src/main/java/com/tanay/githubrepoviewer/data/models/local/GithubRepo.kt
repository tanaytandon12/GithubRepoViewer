package com.tanay.githubrepoviewer.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubRepo(
    @PrimaryKey
    val id: Int
)