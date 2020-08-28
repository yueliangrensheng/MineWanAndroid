package com.yazao.wan.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/8/27
 */


fun CoroutineScope.delayLaunch(timeMills: Long, init: CoroutineScope.() -> Unit): Job {
    check(timeMills >= 0) { "timeMills must be positive" }
    return launch {
        delay(timeMills)
        init()
    }
}
