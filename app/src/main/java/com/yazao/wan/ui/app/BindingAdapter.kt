package com.yazao.wan.ui.app

import android.content.Context
import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.yazao.wan.base.BasePagingDataAdapter
import com.yazao.wan.entity.BannerData
import com.yazao.wan.listener.OnItemClickListener
import com.yazao.wan.listener.OnItemLongClickListener
import com.yazao.wan.weight.ErrorReload
import com.yazao.wan.weight.RequestStatusCode
import com.yazao.wan.weight.RequestStatusView
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/6
 */

/**
 * 绑定 Banner 图片列表和点击事件
 * @param banners Banner 信息列表
 * @param listener 点击事件
 */
@BindingAdapter(value = ["bind:banners", "bind:bannerClick"], requireAll = false)
fun loadBannerImg(banner: Banner, banners: List<BannerData>?, listener: OnBannerListener) {
    if (banners.isNullOrEmpty()) return

    val images = arrayListOf<String>()
    banners.forEach {
        images.add(it.imagePath)
    }

    banner.setImages(images)
        .setImageLoader(GlideLoader())
        .setBannerStyle(BannerConfig.RIGHT)
        .setDelayTime(5000)
        .start()

    banner.setOnBannerListener(listener)

}

class GlideLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }

}

/**
 * 绑定 WevView 的 Url
 */
@BindingAdapter(value = ["bind:url"])
fun bindWebViewUrl(webView: WebView, url: String?) {
    if (url.isNullOrBlank()) return

    webView.loadUrl(url)
}

/**
 * 错误处理绑定
 */
@BindingAdapter(value = ["bind:requestStatusCode", "bind:errorReload"], requireAll = false)
fun bindRequestStatus(
    statusView: RequestStatusView,
    requestStatusCode: RequestStatusCode?,
    errorReload: ErrorReload?
) {
    statusView.injectRequestStatus(requestStatusCode ?: RequestStatusCode.Succeed)
    statusView.errorReload = errorReload
}

/**
 * 绑定 ViewPager 属性
 */
@BindingAdapter(value = ["bind:limitOffset"])
fun bindViewPageOffsetLimit(viewPager: ViewPager, limitOffset: Int = 1) {
    viewPager.offscreenPageLimit = limitOffset
}

@BindingAdapter(value = ["bind:reversed", "bind:transformer"], requireAll = false)
fun bindViewPageTransformer(
    viewPager: ViewPager,
    reversed: Boolean,
    transformer: ViewPager.PageTransformer
) {
    viewPager.setPageTransformer(reversed, transformer)
}


/**
 *  绑定 SwipeRefreshLayout 颜色，刷新状态，监听事件
 */
@BindingAdapter(
    value = ["bind:refreshColor", "bind:refreshState", "bind:refreshListener"],
    requireAll = false
)
fun bindRefreshLayout(
    refreshLayout: SwipeRefreshLayout,
    @ColorRes color: Int,
    refreshState: Boolean,
    refreshListener: SwipeRefreshLayout.OnRefreshListener
) {
    refreshLayout.setColorSchemeResources(color)
    refreshLayout.isRefreshing = refreshState
    refreshLayout.setOnRefreshListener(refreshListener)
}


/**
 * hasFixedSize: recyclerView是否固定高度
 *
 */
@BindingAdapter(value = ["bind:hasFixedSize"], requireAll = false)
fun bindRecyclerView(
    recyclerView: RecyclerView,
    hasFixedSize: Boolean,
) {
    recyclerView.setHasFixedSize(hasFixedSize)
}

/**
 * 绑定 Paging3 adapter 点击事件
 */
@BindingAdapter(value = ["bind:pagingItemClick", "bind:pagingItemLongClick"], requireAll = false)
fun bindPagingItemClick(
    recyclerView: RecyclerView,
    listener: OnItemClickListener?,
    longListener: OnItemLongClickListener?
) {

    val adapter = recyclerView.adapter ?: return

    val tarAdapter = when (adapter) {
        is BasePagingDataAdapter<*, *> -> adapter
        is ConcatAdapter -> findBasePagingAdapterInConcatAdapter(adapter)
        else -> null
    }

    tarAdapter?.run {
        itemClickListener = listener
        itemLongClickListener = longListener
    } ?: return
}

private fun findBasePagingAdapterInConcatAdapter(mergeAdapter: ConcatAdapter): BasePagingDataAdapter<*, *>? {
    val adapterList = mergeAdapter.adapters

    for (i in adapterList.indices) {
        val adapter = adapterList[i]
        if (adapter is BasePagingDataAdapter<*, *>) return adapter
    }

    return null
}

