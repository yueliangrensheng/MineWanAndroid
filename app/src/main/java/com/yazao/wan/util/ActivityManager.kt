package com.yazao.wan.util

import android.app.Activity

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/8/23
 */
object ActivityManager {
    private val activityies = mutableListOf<Activity>()

    fun addActivity(activity: Activity) {
        activityies.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (activityies.contains(activity)) {
            activityies.remove(activity)
        }
    }



}