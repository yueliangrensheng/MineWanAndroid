package com.yazao.wan.ui.home

import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentHomeArticleLayoutBinding
import com.yazao.wan.ui.app.AppViewModel
import com.yazao.wan.ui.main.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeArticleFragment : BaseFragment<FragmentHomeArticleLayoutBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()
    private val mLoginViewModel by sharedViewModel<MainViewModel>()
    private val mViewModel by viewModel<HomeArticleViewModel>()
//    private val mAdapter by lifecycleScope.inject<HomeArticlePagingAdapter>()

    override fun getLayoutID(): Int = R.layout.fragment_home_article_layout

    override fun requestNetData() {
        launch {
            mViewModel.getHomeArticlesByRoomCache()
        }
    }

    override fun initViewsAndEvents() {

    }

}
