package com.yazao.wan.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.yazao.lib.toast.XToast
import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentHomeArticleLayoutBinding
import com.yazao.wan.listener.OnItemClickListener
import com.yazao.wan.listener.OnItemLongClickListener
import com.yazao.wan.ui.app.AppViewModel
import com.yazao.wan.ui.app.PagingLoadStateAdapter
import kotlinx.coroutines.launch
import com.yazao.wan.ui.main.MainViewModel
import com.yazao.wan.ui.websitedetail.WebsiteDetailFragment
import com.yazao.wan.weight.RequestStatusCode
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.scope.lifecycleScope
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeArticleFragment : BaseFragment<FragmentHomeArticleLayoutBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()
    private val mViewModel by viewModel<HomeArticleViewModel>()
    private val mLoginViewModel by sharedViewModel<MainViewModel>()
    private val mAdapter by lifecycleScope.inject<HomeArticlePagingAdapter>()

    private var isFirstObserver = true

    override fun getLayoutID(): Int = R.layout.fragment_home_article_layout

    @OptIn(ExperimentalPagingApi::class)
    override fun requestNetData() {
        launch {
            mViewModel.getHomeArticlesByRoomCache()
                .catch { mBinding?.statusCode = RequestStatusCode.Error }
                .collectLatest { mAdapter.submitData(it) }
        }

        mAppViewModel.reloadHomeData.observe(this, Observer {
            if (it) mAdapter.refresh()
        })


    }

    @OptIn(ExperimentalPagingApi::class)
    override fun initViewsAndEvents() {

        mBinding?.run {

            refreshColor = R.color.colorAccent
            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                mAdapter.refresh()
            }

            adapter = mAdapter.apply {

            }.withLoadStateFooter(
                PagingLoadStateAdapter { mAdapter.retry() }
            )


            itemClick = OnItemClickListener { position, _ ->
                mAdapter.getItemData(position = position)?.let { homeArticleDetail ->
                    WebsiteDetailFragment.viewDetail(
                        findNavController(),
                        R.id.action_mainFragment_to_websiteDetailFragment,
                        homeArticleDetail.link
                    )
                }
            }

            itemLongClick = OnItemLongClickListener { position, view ->
                mAdapter.getItemData(position)?.let {
                    XToast.show(it.author)
                }
            }


        }
    }

}
