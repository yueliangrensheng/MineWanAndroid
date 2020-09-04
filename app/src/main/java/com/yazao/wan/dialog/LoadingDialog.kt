package com.yazao.wan.dialog

import android.content.Context
import android.view.Gravity
import com.yazao.dialog.XDialog
import com.yazao.wan.R

class LoadingDialog(context: Context /*, @LayoutRes layoutResId: Int */) {
    private val loadingDialog: XDialog by lazy {
        XDialog.Builder(context)
            .setLayoutRes(R.layout.dailog_loading_layout)
            .setCancelable(false)
            .setGravity(Gravity.CENTER)
            .setCanceledOnTouchOutside(false)
            .build()
    }

    init {

    }

    fun showDialog() {
        if (!loadingDialog.isShowing)
            loadingDialog.show()
    }

    fun hideDialog() {
        if (loadingDialog.isShowing)
            loadingDialog.hide()
    }
}
