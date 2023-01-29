package com.example.paging3sample

import com.example.paging3sample.api.GithubService

object MainRepository {

    private val githubService = GithubService.create()

    fun repoPagingSource() = RepoPagingSource(githubService)
}