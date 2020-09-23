package com.yazao.wan.ui.main

import androidx.navigation.fragment.findNavController
import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentMainBinding
import com.yazao.wan.ext.onChange
import com.yazao.wan.ui.app.AppViewModel
import com.yazao.wan.ui.home.HomeArticleFragment
import com.yazao.wan.ui.hot.HotProjectFragment
import com.yazao.wan.ui.system.KnowledgeSystemFragment
import com.yazao.wan.ui.userarticle.UserArticleFragment
import com.yazao.wan.ui.websitedetail.WebsiteDetailFragment
import com.yazao.wan.ui.wxchapter.WxChapterFragment
import com.yazao.wan.util.screenWidth
import com.yazao.wan.weight.viewpager.GalleryTransformer
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutID(): Int = R.layout.fragment_main

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    private val mViewModel by sharedViewModel<MainViewModel>()

    private val mAdapter by lazy {
        MainFragmentPagerAdapter(
            childFragmentManager, arrayListOf(

                HomeArticleFragment(),

                HotProjectFragment(),

                KnowledgeSystemFragment(),

                UserArticleFragment(),

                WxChapterFragment()
            )
        )
    }

    override fun initViewsAndEvents() {

        //设置banner的宽高属性
        banner.let {
            it.layoutParams = it.layoutParams.apply {
                width = screenWidth
                height = (width * 0.45f).toInt()
            }
        }

        //设置 xml布局中的数据
        mBinding?.run {
            viewModel = mViewModel
            //设置banner的点击事件
            listener = OnBannerListener { position ->

                mViewModel.banners.value?.let {
                    //跳转 WebView
                    WebsiteDetailFragment.viewDetail(
                        findNavController(),

                        R.id.action_mainFragment_to_websiteDetailFragment,

                        it[position].url,

                        it[position].imagePath,
                        it[position].title,
                        it[position].desc
                    )
                }
            }

            //viewpager
            adapter = mAdapter
            limitOffset = mAdapter.count
            transformer = GalleryTransformer()
            mainViewpager.onChange(
                pageScrolled = { position, positionOffset, positionOffsetPixels ->
                    pageScrolled(
                        position,
                        positionOffset,
                        positionOffsetPixels
                    )
                },
                pageSelected = { pageSelected() },
                pageStateChange = { pageStateChange() }
            )
        }

        text.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_searchFragment)M
            requestNetData()
        }
    }

    private fun pageStateChange() {
    }

    private fun pageSelected() {
    }

    private fun pageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.i("pageScrolled  position = $position , positionOffset = $positionOffset , positionOffsetPixels = $positionOffsetPixels")
    }

    override fun requestNetData() {
        //获取banner 接口数据
        mViewModel.getBanners()
    }


}