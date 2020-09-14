package com.yazao.wan.ui.app

import android.content.Context
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yazao.wan.entity.BannerData
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
