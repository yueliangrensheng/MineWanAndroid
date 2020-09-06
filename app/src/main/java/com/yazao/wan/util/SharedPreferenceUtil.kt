package com.yazao.wan.util

import android.content.Context
import androidx.core.content.edit


/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/6
 */

private const val SHARED_PREFERENCE_NAME: String = "com.yazao.wan.preference"


fun Context.getString(key: String, default: String = ""): String {
    val sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    return sp.getString(key, default) ?: ""
}

fun Context.putString(key: String, bannerJson: String) {
    val sp = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    sp.edit {
        putString(key, bannerJson)
    }
}
