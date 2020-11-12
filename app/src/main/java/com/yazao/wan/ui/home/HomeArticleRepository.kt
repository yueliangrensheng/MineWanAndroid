package com.yazao.wan.ui.home

import com.yazao.wan.data.WanDatabase
import com.yazao.wan.data.db.HomeArticleDetail
import com.yazao.wan.net.ApiService
import com.yazao.wan.util.PreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeArticleRepository(private val api: ApiService, val db: WanDatabase) {


    val pagingSourceFactory = {
        db.homeArticleCacheDao().fetchAllHomeArticleCache()
    }

    suspend fun loadTops():MutableList<HomeArticleDetail> ? =
        withContext(Dispatchers.IO){
            val cookie = PreferenceHelper
        }
}
