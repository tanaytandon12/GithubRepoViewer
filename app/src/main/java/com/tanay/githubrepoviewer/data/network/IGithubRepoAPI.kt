package com.tanay.githubrepoviewer.data.network

import com.tanay.githubrepoviewer.data.models.RepoResult
import com.tanay.githubrepoviewer.data.models.network.GithubRepoResponse
import javax.inject.Inject

interface IGithubRepoAPI {
    suspend fun repoList(page: Int, pageCount: Int): RepoResult<List<GithubRepoResponse>>
}

class GithubRepoAPI @Inject constructor(private val networkAPI: NetworkAPI) : IGithubRepoAPI {

    override suspend fun repoList(page: Int, pageCount: Int): RepoResult<List<GithubRepoResponse>> {
        return try {
            RepoResult.Success(networkAPI.fetchRepos(pageNo = page, count = pageCount))
        } catch (e: Exception) {
            e.printStackTrace()
            RepoResult.Failure()
        }
    }
}