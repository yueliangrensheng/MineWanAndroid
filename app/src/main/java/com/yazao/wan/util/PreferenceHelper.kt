package com.yazao.wan.util

import android.content.Context

object PreferenceHelper {
    private const val USER_KEY_COOKIE: String = "wan.user.cookie"
    private const val KEY_BANNER_CACHE: String = "wan.banner.cache"

    fun getBannerCache(context: Context) = context.getString(KEY_BANNER_CACHE)

    fun putBannerCache(context: Context, bannerJson: String) =
        context.putString(KEY_BANNER_CACHE, bannerJson)

    fun fetchCookie(context: Context): String = context.getString(USER_KEY_COOKIE)
}