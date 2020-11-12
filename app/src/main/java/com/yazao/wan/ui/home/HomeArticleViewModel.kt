package com.yazao.wan.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import com.yazao.wan.ui.app.constPagerConfig

class HomeArticleViewModel(
    private val repository: HomeArticleRepository
) : ViewModel() {
    @ExperimentalPagingApi
    fun getHomeArticlesByRoomCache() = Pager(
        constPagerConfig,
//        remoteMediator = HomeArticleRemoteMediator(repository, repository.db),
        pagingSourceFactory = repository.pagingSourceFactory
    )



}