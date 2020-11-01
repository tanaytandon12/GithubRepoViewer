package com.tanay.githubrepoviewer.data.network

import com.tanay.githubrepoviewer.data.models.network.GithubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkAPI {

    @GET("/orgs/octokit/repos")
    suspend fun fetchRepos(
        @Query("page") pageNo: Int,
        @Query("per_page") count: Int
    ): List<GithubRepoResponse>
}