package com.yazao.wan.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.yazao.wan.ui.app.constPagerConfig

class HomeArticleViewModel(
    private val repository: HomeArticleRepository
) : ViewModel() {

    fun getHomeArticlesByRoomCache() = Pager(
        constPagerConfig,
        remoteMediator = HomeArticleRemoteMediator(repository, repository.db),
        pagingSourceFactory = repository.pagingSourceFactory
    ).flow.cachedIn(viewModelScope)

}
