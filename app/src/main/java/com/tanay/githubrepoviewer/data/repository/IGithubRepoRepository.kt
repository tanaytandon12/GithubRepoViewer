package com.tanay.githubrepoviewer.data.repository

import androidx.paging.DataSource
import com.tanay.githubrepoviewer.data.database.IGithubRepoDao
import com.tanay.githubrepoviewer.data.models.GithubRepoWrapper
import com.tanay.githubrepoviewer.data.models.RepoResult
import com.tanay.githubrepoviewer.data.models.local.GithubRepo
import com.tanay.githubrepoviewer.data.network.IGithubRepoAPI
import java.lang.Exception
import javax.inject.Inject

interface IGithubRepoRepository {

    suspend fun fetchRepos(page: Int, pageSize: Int): RepoResult<GithubRepoWrapper>

    fun repos(): DataSource.Factory<Int, GithubRepo>
}

class GithubRepoRepository @Inject constructor(
    private val mGithubRepoDao: IGithubRepoDao,
    private val mGithubRepoAPI: IGithubRepoAPI
) : IGithubRepoRepository {

    private var mLimitNotReached = true

    override suspend fun fetchRepos(page: Int, pageSize: Int): RepoResult<GithubRepoWrapper> {
        return try {
            if (mLimitNotReached) {
                val apiResponse = mGithubRepoAPI.repoList(page, pageSize)
                if (apiResponse is RepoResult.Success) {
                    mLimitNotReached =
                        apiResponse.data?.isNotEmpty() == true && pageSize == apiResponse.data.size
                    apiResponse.data?.let {
                        mGithubRepoDao.insert(it.map {
                            GithubRepo(
                                id = it.id,
                                name = it.name,
                                description = it.description,
                                openIssues = it.openIssues,
                                licenseName = it.license?.name,
                                licenseUrl = it.license?.url,
                                hasAdminPermission = it.permissions?.admin ?: false,
                                hasPullPermission = it.permissions?.pull ?: false,
                                hasPushPermission = it.permissions?.push ?: false
                            )
                        })
                    }
                    RepoResult.Success(GithubRepoWrapper(mLimitNotReached))
                } else {
                    RepoResult.Failure(
                        errorMessage = (apiResponse as RepoResult.Failure).errorMessage,
                        descriptor = apiResponse.descriptor
                    )
                }
            } else {
                RepoResult.Success(GithubRepoWrapper(limitReached = true))
            }
        } catch (e: Exception) {
            RepoResult.Failure()
        }
    }

    override fun repos(): DataSource.Factory<Int, GithubRepo> = mGithubRepoDao.repos()
}