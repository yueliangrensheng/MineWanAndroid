package com.yazao.wan.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yazao.lib.xbase.WBaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment<VB : ViewDataBinding> : WBaseFragment(), CoroutineScope by MainScope() {

    protected var mBinding: VB? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true

        if (mBinding == null) {
            mBinding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        }

        return if (mBinding != null) {
            mBinding!!.root.apply {
                (parent as? ViewGroup)?.removeView(this)
            }
        } else super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding?.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
        mBinding?.unbind()
    }


    override fun getBundleArguments(arguments: Bundle?) {
    }

    override fun onFirstUserVisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun isFitDarkMode(): Boolean = false

    override fun isFitDataBinding(): Boolean = true

}
