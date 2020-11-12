package com.yazao.wan.entity

import com.yazao.wan.data.db.HomeArticleDetail

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/10/11
 */

data class ArticleData(
    val curPage: Int,
    val datas: MutableList<HomeArticleDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)