package com.yazao.wan.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.yazao.wan.data.WanDatabase
import com.yazao.wan.data.db.HomeArticleDetail

@ExperimentalPagingApi
class HomeArticleRemoteMediator(repository: HomeArticleRepository, db: WanDatabase) :
    RemoteMediator<Int, HomeArticleDetail>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HomeArticleDetail>
    ): MediatorResult {

    }

}
