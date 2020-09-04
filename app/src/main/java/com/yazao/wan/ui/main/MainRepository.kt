package com.yazao.wan.ui.main

import com.yazao.wan.entity.BannerData
import com.yazao.wan.net.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val api: ApiService) {

    fun getCachedBanners(): MutableList<BannerData>? = null

    suspend fun getBanners() =
        withContext(Dispatchers.IO) {
            api.homeBanner().data
        }
}
