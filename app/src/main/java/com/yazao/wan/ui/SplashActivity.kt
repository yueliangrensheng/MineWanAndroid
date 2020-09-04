package com.yazao.wan.ui

import android.view.KeyEvent
import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.base.BaseActivity
import com.yazao.wan.databinding.ActivitySplashBinding
import com.yazao.wan.scope.delayLaunch
import com.yazao.wan.ui.app.MainActivity
import org.jetbrains.anko.startActivity

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/8/24
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getLayoutID(): Int = R.layout.activity_splash

    override fun initData() {
        Log.d("----- " + SplashActivity::class.simpleName)
        delayLaunch(2000) {
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun isFullScreen(): Boolean = true

    override fun isTransparentStatusBar(): Boolean = true
}