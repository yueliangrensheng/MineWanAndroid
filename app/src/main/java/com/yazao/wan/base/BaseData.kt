package com.yazao.wan.base

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/4
 */
data class BaseData<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)