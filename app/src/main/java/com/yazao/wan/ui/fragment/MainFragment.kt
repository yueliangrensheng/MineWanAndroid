package com.yazao.wan.ui.fragment

import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.databinding.FragmentMainBinding
import com.yazao.wan.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initViewsAndEvents() {
        Log.i(MainFragment::class.java.canonicalName);
    }


}