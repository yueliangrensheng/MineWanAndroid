package com.yazao.wan

import com.yazao.lib.toast.XToast
import com.yazao.lib.xbase.WBaseApplication

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
    }
}