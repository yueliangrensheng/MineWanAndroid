package com.yazao.wan.ui.home

import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentHomeArticleLayoutBinding
import com.yazao.wan.ui.app.AppViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeArticleFragment : BaseFragment<FragmentHomeArticleLayoutBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()
    private val mViewModel by viewModel<HomeArticleViewModel>()

    override fun getLayoutID(): Int = R.layout.fragment_home_article_layout

    override fun requestNetData() {
        launch {
            mViewModel.getHomeArticlesByRoomCache()
        }
    }

    override fun initViewsAndEvents() {

    }

}
