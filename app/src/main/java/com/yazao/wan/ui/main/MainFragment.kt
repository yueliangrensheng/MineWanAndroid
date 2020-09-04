package com.yazao.wan.ui.main

import com.yazao.wan.R
import com.yazao.wan.databinding.FragmentMainBinding
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.ui.app.AppViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutID(): Int = R.layout.fragment_main

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    private val mViewModel by sharedViewModel<MainViewModel>()



    override fun initViewsAndEvents() {

    }


}