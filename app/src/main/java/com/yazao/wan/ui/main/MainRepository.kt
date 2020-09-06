package com.yazao.wan.ui.main

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yazao.wan.App
import com.yazao.wan.entity.BannerData
import com.yazao.wan.net.ApiService
import com.yazao.wan.util.PreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val api: ApiService) {

    fun getCachedBanners(): MutableList<BannerData>? = Gson().fromJson(
        PreferenceHelper.getBannerCache(App.instance),
        object : TypeToken<MutableList<BannerData>>() {}.type
    )

    suspend fun getBanners() =
        withContext(Dispatchers.IO) {
            api.homeBanner().data
        }
}
