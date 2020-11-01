package com.tanay.githubrepoviewer.data.models.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubRepoResponse(
    val id: Int,
    val name: String? = null,
    val description: String? = null,
    @Json(name = "open_issues")
    val openIssues: Int? = null,
    val license: License? = null,
    val permissions: Permissions? = null
)

@JsonClass(generateAdapter = true)
data class License(val name: String, val url: String? = null)

@JsonClass(generateAdapter = true)
data class Permissions(
    val admin: Boolean = false,
    val pull: Boolean = false,
    val push: Boolean = false
)