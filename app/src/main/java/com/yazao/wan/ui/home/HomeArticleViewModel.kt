package com.yazao.wan.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.Pager
import com.yazao.wan.ui.app.constPagerConfig

class HomeArticleViewModel(
    private val repository: HomeArticleRepository
) : ViewModel() {

    fun getHomeArticlesByRoomCache() = Pager(
        config = constPagerConfig,
        remoteMediator = null,
        pagingSourceFactory = repository.pagingSourceFactory
    )

}
