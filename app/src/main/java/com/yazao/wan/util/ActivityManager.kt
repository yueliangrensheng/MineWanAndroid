package com.yazao.wan.util

import android.app.Activity

/**
 * Description : Activity栈管理
 * Author : yueliangrensheng
 * Date : 2020/8/23
 */
object ActivityManager {
    private val activities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
        }
    }


    fun getTopActivity(): Activity? =
        if (activities.isEmpty()) null else activities[activities.size - 1]

    fun finishAll() =
        activities.filter { it.isFinishing }.forEach { it.finish() }
}