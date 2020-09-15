package com.yazao.wan.weight

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.yazao.wan.R
import com.yazao.wan.ext.drawableValue
import com.yazao.wan.ext.stringValue

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/14
 */

open class RequestStatusView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    TextViewCompoundDrawable(context, attrs, defStyleAttr) {

    var errorReload: ErrorReload? = null

    init {
        injectRequestStatus(RequestStatusCode.Loading)
    }

    fun injectRequestStatus(statusCode: RequestStatusCode) {
        val statusDrawable: Drawable?

        when (statusCode) {
            RequestStatusCode.Loading -> {
                statusDrawable = context.drawableValue(R.drawable.tag_loading)
                text = context.stringValue(R.string.loading_data)
            }
            RequestStatusCode.Empty -> {
                statusDrawable = context.drawableValue(R.drawable.tag_empty)
                text = context.stringValue(R.string.empty_data)
            }
            RequestStatusCode.Error -> {
                statusDrawable = context.drawableValue(R.drawable.tag_load_error)
                text = context.stringValue(R.string.reload_data)
                setOnClickListener {
                    errorReload?.reload()
                }
            }
            RequestStatusCode.Succeed -> {
                isVisible = false
                return
            }
        }

        isVisible = true
        statusDrawable?.setBounds(
            0,
            0,
            statusDrawable.minimumWidth / 2,
            statusDrawable.minimumHeight / 2
        )
        compoundDrawablePadding = 12
        setCompoundDrawables(null, statusDrawable, null, null)
        setTextColor(Color.parseColor("#FFCCCCCC"))
    }

}

enum class RequestStatusCode {
    Empty, Error, Loading, Succeed
}