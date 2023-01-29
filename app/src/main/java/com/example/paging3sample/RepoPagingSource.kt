package com.example.paging3sample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3sample.api.GithubService
import com.example.paging3sample.model.Repo

class RepoPagingSource(private val githubService: GithubService) : PagingSource<Int, Repo>() {

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val repoResponse = githubService.searchRepos(page, pageSize)
            val repoItems = repoResponse.items
            LoadResult.Page(
                data = repoItems,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}