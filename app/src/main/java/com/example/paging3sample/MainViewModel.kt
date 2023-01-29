package com.example.paging3sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3sample.model.Repo
import kotlinx.coroutines.flow.Flow

private const val PAGE_SIZE = 30

class MainViewModel : ViewModel() {

    val items: Flow<PagingData<Repo>> = Pager(
        config = PagingConfig(PAGE_SIZE),
        pagingSourceFactory = { MainRepository.repoPagingSource() }
    ).flow.cachedIn(viewModelScope)
}