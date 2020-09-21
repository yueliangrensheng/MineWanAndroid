package com.yazao.wan.ui.app

import android.content.Context
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.yazao.wan.entity.BannerData
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
fun bindOffsetViewPageLimit(viewPager: ViewPager, limitOffset: Int = 1) {
    viewPager.offscreenPageLimit = limitOffset
}
