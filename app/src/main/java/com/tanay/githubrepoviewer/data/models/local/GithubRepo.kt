package com.tanay.githubrepoviewer.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GithubRepo(
    @PrimaryKey
    val id: Int,
    val name: String? = null,
    val description: String? = null,
    val openIssues: Int? = null,
    val licenseName: String? = null,
    val licenseUrl: String? = null,
    val hasAdminPermission: Boolean = false,
    val hasPullPermission: Boolean = false,
    val hasPushPermission: Boolean = false
)