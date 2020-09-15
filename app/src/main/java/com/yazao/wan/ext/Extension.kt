package com.yazao.wan.ext

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/14
 */

fun Context.drawableValue(@DrawableRes drawableRes: Int) =
    ContextCompat.getDrawable(this, drawableRes)

fun Context.stringValue(@StringRes stringRes: Int) = resources.getString(stringRes)