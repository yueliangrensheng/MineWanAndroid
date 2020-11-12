package com.yazao.wan.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.yazao.wan.data.WanDatabase
import com.yazao.wan.data.db.HomeArticleDetail

@OptIn(ExperimentalPagingApi::class)
class HomeArticleRemoteMediator(
    private val repository: HomeArticleRepository,
    private val wanDatabase: WanDatabase
) : RemoteMediator<Int, HomeArticleDetail>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HomeArticleDetail>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {

            }

            LoadType.PREPEND -> {
            }
            LoadType.APPEND -> {
            }
        }

        return try {
            val artiles = if (page == 0) mutableListOf<HomeArticleDetail>().apply {
                addAll(repository.loadTops() ?: mutableListOf())
            } else {
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }


    }

}
