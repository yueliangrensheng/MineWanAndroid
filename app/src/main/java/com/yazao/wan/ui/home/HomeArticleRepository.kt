package com.yazao.wan.ui.home

import com.yazao.wan.data.WanDatabase
import com.yazao.wan.net.ApiService

class HomeArticleRepository(private val api: ApiService, val db: WanDatabase) {

    val pagingSourceFactory = {
        db.homeArticleCacheDao().fetchAllHomeArticleCache()
    }
}
