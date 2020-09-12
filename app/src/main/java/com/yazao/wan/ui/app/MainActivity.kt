package com.yazao.wan.ui.app

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.yazao.wan.R
import com.yazao.wan.databinding.ActivityMainBinding
import com.yazao.wan.base.BaseActivity
import com.yazao.wan.dialog.LoadingDialog
import com.yazao.wan.ui.main.MainFragment
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var mAvailableTypeCount = 0

    private val mAppViewModel by viewModel<AppViewModel>()

    private val mLoadingDialog by lifecycleScope.inject<LoadingDialog>()

    private val mConnectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val mNetworkRequest by lazy {
        NetworkRequest.Builder().build()
    }

    private val mNetStateCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                mAvailableTypeCount++
                checkNetState()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                mAvailableTypeCount--
                checkNetState()
            }
        }
    }

    private fun checkNetState() {
        mBinding.netAvailable = mAvailableTypeCount > 0
    }

    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initData() {

        mConnectivityManager.registerNetworkCallback(mNetworkRequest, mNetStateCallback)

        mAppViewModel.showLoadingProgress.observe(this, {
            if (it) mLoadingDialog.showDialog()
            else mLoadingDialog.hideDialog()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mConnectivityManager.unregisterNetworkCallback(mNetStateCallback)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.first()
            .childFragmentManager.fragments.last().let {
                if (it is MainFragment) {
                    startActivity(Intent(Intent.ACTION_MAIN).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        addCategory(Intent.CATEGORY_HOME)
                    });return
                }
            }
        super.onBackPressed()
    }

    override fun isTransparentStatusBar(): Boolean = true
}