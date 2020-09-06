package com.yazao.wan.ui.app

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yazao.wan.entity.BannerData
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/6
 */

@BindingAdapter(value = ["bind:banners"], requireAll = false)
fun loadBannerImg(banner: Banner, banners: List<BannerData>?) {
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

}

class GlideLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }

}
