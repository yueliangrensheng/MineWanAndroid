package com.yazao.wan.ui.fragment

import androidx.navigation.Navigation
import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.databinding.FragmentMainBinding
import com.yazao.wan.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initViewsAndEvents() {
        Log.i(MainFragment::class.java.canonicalName);

        fragment_main_tv.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }

}