package com.yazao.wan.ui.websitedetail

import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.*
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentWebsiteDetailBinding
import kotlinx.android.synthetic.main.fragment_website_detail.*
import java.lang.Exception

/**
 * 文章，banner 等网页链接显示
 */
class WebsiteDetailFragment : BaseFragment<FragmentWebsiteDetailBinding>() {

    private val url: String by lazy {
        arguments?.getString("url") ?: ""
    }

    override fun getLayoutID(): Int = R.layout.fragment_website_detail


    override fun requestNetData() {
    }

    override fun initViewsAndEvents() {

        // set url
        mBinding?.url = url

        //设置 WebView 属性
        mBinding?.webView?.let {

            it.settings.apply {

                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                allowFileAccess = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
                useWideViewPort = true
                loadWithOverviewMode = true
                setSupportMultipleWindows(true)
                setSupportZoom(true)
                setGeolocationEnabled(true)
                builtInZoomControls = true
                displayZoomControls = true
                setAppCacheEnabled(true)
                domStorageEnabled = true
                cacheMode = WebSettings.LOAD_NO_CACHE
            }

            it.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url.isNullOrBlank()) return false

                    if (!url.matches(Regex("(http|https)?://(\\S)+"))) {

                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } catch (e: Exception) {

                        }
                        return true
                    }

                    view?.loadUrl(url)
                    return true
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {

                    val url = request?.url.toString()

                    if (url.isNullOrBlank()) return false

                    if (!url.matches(Regex("(http|https)?://(\\S)+"))) {

                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } catch (e: Exception) {

                        }
                        return true
                    }

                    view?.loadUrl(url)
                    return true
                }
            }

            it.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    if (newProgress > 85) {
                        mBinding?.webShare?.isVisible = true
//                        mBinding?.webShare?.visibility = View.VISIBLE
                        mBinding?.loading?.isVisible = false
                    }
                }
            }
        }

        // back click
        mBinding?.webBack?.setOnClickListener {

            findNavController().popBackStack()
        }

    }


    companion object {
        fun viewDetail(
            controller: NavController,
            @IdRes idRes: Int,
            url: String
        ) {
            if (url.isBlank()) return

            Log.i("url =  $url")
            controller.navigate(idRes, bundleOf("url" to url))
        }
    }
}
