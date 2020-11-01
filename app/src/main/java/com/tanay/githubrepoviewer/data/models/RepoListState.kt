package com.tanay.githubrepoviewer.data.models

sealed class RepoListState {

    object Init : RepoListState()
    object Loading : RepoListState()
    data class Failure(val errorMessage: String? = null) : RepoListState()
    object Fetched : RepoListState()
    object LimitReached : RepoListState()
}