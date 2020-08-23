package com.yazao.wan.base

import android.os.Bundle
import com.yazao.lib.net.NetUtil
import com.yazao.lib.toast.XToast
import com.yazao.lib.xbase.WBaseActivity
import com.yazao.wan.util.ActivityManager

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/8/23
 */
abstract class BaseActivity : WBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
    }

    override fun isNoStateBar(): Boolean {
        return true
    }

    override fun isFullScreen(): Boolean {
        return false
    }

    override fun isNoTitle(): Boolean {
        return true
    }

    override fun onNetWorkConnected(type: NetUtil.NetType?) {
        when (type) {
            NetUtil.NetType.WIFI -> XToast.show("WiFi已连接")
            NetUtil.NetType.CMWAP, NetUtil.NetType.CMNET -> XToast.show("移动数据已连接")
            else -> XToast.show("网络已连接")
        }
    }

    override fun onNetWorkDisConnected() {
        XToast.show("网络已断开")
    }

    override fun getBundleExtras(extras: Bundle?) {

    }
}