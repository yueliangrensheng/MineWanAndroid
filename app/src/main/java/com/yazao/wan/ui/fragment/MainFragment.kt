package com.yazao.wan.ui.fragment

import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.databinding.FragmentMainBinding
import com.yazao.wan.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutID(): Int = R.layout.fragment_main

    override fun initViewsAndEvents() {
        Log.i(MainFragment::class.simpleName + " -- initViewsAndEvents")
    }

    override fun onFirstUserVisible() {
        super.onFirstUserVisible()
        Log.i(MainFragment::class.simpleName + " -- onFirstUserVisible")
    }

    override fun onFirstUserInvisible() {
        super.onFirstUserInvisible()
        Log.i(MainFragment::class.simpleName + " -- onFirstUserInvisible")
    }

}