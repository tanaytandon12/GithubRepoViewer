package com.tanay.githubrepoviewer.data.models

sealed class RepoResult<out R> {

    data class Success<out T>(val data: T? = null) : RepoResult<T>()
    data class Failure(val errorMessage: String? = null, val descriptor: String? = null) :
        RepoResult<Nothing>()
}