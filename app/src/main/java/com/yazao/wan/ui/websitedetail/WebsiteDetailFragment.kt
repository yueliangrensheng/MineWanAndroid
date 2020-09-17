package com.yazao.wan.ui.websitedetail

import android.content.Intent
import android.graphics.Bitmap
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
import com.yazao.lib.share.XShare
import com.yazao.lib.share.bean.XShareBean
import com.yazao.lib.share.listener.OnShareDialogClickListener
import com.yazao.lib.share.listener.impl.SimpleShareDialogClickListenerImpl
import com.yazao.lib.toast.XToast
import com.yazao.lib.xlog.Log
import com.yazao.wan.R
import com.yazao.wan.base.BaseFragment
import com.yazao.wan.databinding.FragmentWebsiteDetailBinding
import com.yazao.wan.weight.RequestStatusCode
import kotlinx.android.synthetic.main.fragment_website_detail.*
import java.lang.Exception

/**
 * 文章，banner 等网页链接显示
 */
class WebsiteDetailFragment : BaseFragment<FragmentWebsiteDetailBinding>() {

    var isReceivedError: Boolean? = false

    private val url: String by lazy {
        arguments?.getString("url") ?: ""
    }
    //分享 封面url
    private val shareCoverUrl: String? by lazy {
        arguments?.getString("shareCoverUrl")
    }
    //分享 标题
    private val shareTitle: String? by lazy {
        arguments?.getString("shareTitle")
    }
    //分享 描述
    private val shareDesc: String? by lazy {
        arguments?.getString("shareDesc")
    }

    override fun getLayoutID(): Int = R.layout.fragment_website_detail


    override fun requestNetData() {
    }

    override fun initViewsAndEvents() {

        // set url
        mBinding?.url = url

        //set requestStatus
        mBinding?.requestStatus = RequestStatusCode.Loading

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

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    isReceivedError = true
                    mBinding?.webView?.isVisible = false
                    mBinding?.webShare?.isVisible = false
                    mBinding?.loading?.isVisible = true
//                    mBinding?.loading?.injectRequestStatus(RequestStatusCode.Error)
                    mBinding?.requestStatus = RequestStatusCode.Error
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    isReceivedError = true
                    mBinding?.webView?.isVisible = false
                    mBinding?.webShare?.isVisible = false
                    mBinding?.loading?.isVisible = true
                    mBinding?.requestStatus = RequestStatusCode.Error
                }
            }

            it.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)

                    if (isReceivedError!!) return

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

        // share
        mBinding?.webShare?.setOnClickListener {
            showShareDialog()
        }

    }

    private fun showShareDialog() {
        val shareBean = XShareBean()
        shareBean.isShowWX = true
        shareBean.isShowWXCircle = true
        shareBean.isShowQQ = true
        shareBean.isShowQQZone = true
        shareBean.isShowToReport = true
        shareBean.isShowCopyLink = true

        //设置公共参数
        shareBean.shareInfo.coverUrl = shareCoverUrl
        shareBean.shareInfo.title = shareTitle
        shareBean.shareInfo.desc = shareDesc
        shareBean.shareInfo.url = this.url
        shareBean.shareInfo.isWebShare = true

        //微信参数
        shareBean.shareWXInfo.isShowWXMini = false

        //复制链接
        shareBean.shareCopyLinkInfo.copyLink = this.url


        val listener: OnShareDialogClickListener = object : SimpleShareDialogClickListenerImpl() {

            override fun onDialogWXClick() {
                super.onDialogWXClick()
            }

            override fun onDialogWXCircleClick() {
                super.onDialogWXCircleClick()
            }

            override fun onDialogQQClick(state: Int) {
                super.onDialogQQClick(state)
            }

            override fun onDialogQQZoneClick(state: Int) {
                super.onDialogQQZoneClick(state)
            }

            override fun onDialogCopyLinkClick() {
                super.onDialogCopyLinkClick()
                XToast.show("复制成功")
            }

            override fun onDialogReportClick() {
                super.onDialogReportClick()
                XToast.show("举报成功，我们将尽快处理")
            }
        }

        XShare.getInstance().init(activity).setConfig(shareBean).setListener(listener).build()
        XShare.getInstance().showDialog()
    }


    companion object {
        fun viewDetail(
            controller: NavController,
            @IdRes idRes: Int,
            url: String,
            shareCoverUrl: String? = "",
            shareTitle: String? = "",
            shareDesc: String? = "",
        ) {
            if (url.isBlank()) return

            Log.i("url =  $url")
            controller.navigate(
                idRes,
                bundleOf(
                    "url" to url,
                    "shareCoverUrl" to shareCoverUrl,
                    "shareTitle" to shareTitle,
                    "shareDesc" to shareDesc,
                )
            )
        }
    }
}
