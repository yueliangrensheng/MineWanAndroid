package com.yazao.wan.scope

import kotlinx.coroutines.*

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

data class CoroutineCallback(
    var initDispatcher: CoroutineDispatcher? = null,
    var block: suspend () -> Unit = {},
    var onError: (Throwable) -> Unit = {}
)

fun CoroutineScope.safeLaunch(init: CoroutineCallback.() -> Unit): Job {

    val callback = CoroutineCallback().apply { this.init() }
    return launch(
        CoroutineExceptionHandler { _, throwable -> callback.onError(throwable) }
                + (callback.initDispatcher ?: GlobalScope.coroutineContext)
    ) {
        callback.block()
    }
}
