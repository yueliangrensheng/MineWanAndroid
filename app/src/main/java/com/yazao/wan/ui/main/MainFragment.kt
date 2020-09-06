package com.yazao.wan.ui.main

import androidx.navigation.Navigation
import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentMainBinding
import com.yazao.wan.ui.app.AppViewModel
import com.yazao.wan.util.screenWidth
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutID(): Int = R.layout.fragment_main

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    private val mViewModel by sharedViewModel<MainViewModel>()


    override fun initViewsAndEvents() {

        //设置banner的宽高属性
        banner.let {
            it.layoutParams = it.layoutParams.apply {
                width = screenWidth
                height = (width * 0.45f).toInt()
            }
        }

        mBinding?.run {
            viewModel = mViewModel
        }

        text.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_searchFragment)M
            requestNetData()
        }
    }

    override fun requestNetData() {
        //获取banner 接口数据
        mViewModel.getBanners()
    }


}