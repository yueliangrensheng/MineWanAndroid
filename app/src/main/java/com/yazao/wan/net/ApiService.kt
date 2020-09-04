package com.yazao.wan.net

import com.yazao.wan.base.BaseData
import com.yazao.wan.entity.BannerData
import retrofit2.http.GET

interface ApiService {

    /**
     * 首页Banner轮播图
     */
    @GET("/banner/json")
    suspend fun homeBanner() : BaseData<MutableList<BannerData>>
}
