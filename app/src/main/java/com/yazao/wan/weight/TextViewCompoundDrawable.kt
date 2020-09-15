package com.yazao.wan.weight

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/14
 */
open class TextViewCompoundDrawable @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        TextViewCompoundDrawableHelper.preDraw(this, canvas)
        super.onDraw(canvas)
    }

}