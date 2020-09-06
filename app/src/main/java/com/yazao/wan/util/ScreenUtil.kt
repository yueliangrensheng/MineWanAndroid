package com.yazao.wan.util

import android.content.res.Resources

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/6
 */

val screenWidth = Resources.getSystem().displayMetrics.widthPixels

val screenHeight = Resources.getSystem().displayMetrics.heightPixels

val screenDensity = Resources.getSystem().displayMetrics.density

fun Float.dp2px() = screenDensity * this + 0.5f
