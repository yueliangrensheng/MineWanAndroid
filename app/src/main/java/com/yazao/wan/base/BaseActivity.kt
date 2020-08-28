package com.yazao.wan.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yazao.lib.net.NetUtil
import com.yazao.lib.toast.XToast
import com.yazao.lib.xbase.WBaseActivity
import com.yazao.wan.util.ActivityManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Description : Activity基类
 * Author : yueliangrensheng
 * Date : 2020/8/23
 */
abstract class BaseActivity<VB : ViewDataBinding> : WBaseActivity(), CoroutineScope by MainScope() {


    protected open val mBinding: VB by lazy {
        DataBindingUtil.setContentView(this, layoutID) as VB
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.addActivity(this)
        mBinding.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
        mBinding.unbind()
        cancel()
    }

    override fun isNoStateBar(): Boolean {
        return false
    }

    override fun isFullScreen(): Boolean {
        return false
    }

    override fun isNoTitle(): Boolean {
        return false
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

    override fun isFitDarkMode(): Boolean {
        return false
    }

    override fun isTransparentStatusBar(): Boolean {
        return false
    }
}