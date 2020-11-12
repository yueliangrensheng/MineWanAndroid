package com.yazao.wan.net

import com.yazao.wan.base.BaseData
import com.yazao.wan.data.db.HomeArticleDetail
import com.yazao.wan.entity.ArticleData
import com.yazao.wan.entity.BannerData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    /**
     * 首页Banner轮播图
     */
    @GET("/banner/json")
    suspend fun homeBanner(): BaseData<MutableList<BannerData>>

    /**
     * 首页文章
     */
    @GET("/article/list/{page}/json")
    suspend fun homeArticles(@Path("page") page: Int): BaseData<ArticleData>

    /**
     * 置顶文章
     */
    @GET("/article/top/json")
    suspend fun topArticle(@Header("Cookie") cookie: String): BaseData<MutableList<HomeArticleDetail>>


}
