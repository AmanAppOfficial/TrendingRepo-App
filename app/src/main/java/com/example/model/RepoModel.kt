package com.example.model

/**
 * only selected items are parsed
 * which are needed in UI.
 * **/
data class RepoModel(
    val rank: String,
    val repositoryName: String,
    val description: String,
    var selected: Boolean = false
)