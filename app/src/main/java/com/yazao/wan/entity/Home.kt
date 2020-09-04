package com.yazao.wan.entity

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/4
 */
data class BannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)