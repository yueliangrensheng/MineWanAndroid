package com.yazao.wan

import com.yazao.lib.toast.XToast
import com.yazao.lib.xbase.WBaseApplication
import com.yazao.lib.xlog.Log
import com.yazao.wan.module.dataSourceModule
import com.yazao.wan.module.dialogModule
import com.yazao.wan.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/8/23
 */
class App : WBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        init();
    }

    private fun init() {
        // init xtoast
        XToast.init(this)
        // init log
        Log.init().setLogLevel(Log.LogLevel.FULL)
        // init koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()
            modules(
                dataSourceModule,
                viewModelModule,
                dialogModule,

            )
        }
    }
}