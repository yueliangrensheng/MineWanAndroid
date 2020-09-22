package com.yazao.wan.ext

import androidx.viewpager.widget.ViewPager

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/22
 */

fun ViewPager.onChange(
    pageStateChange: ((Int) -> Unit)? = null,
    pageScrolled: ((Int, Float, Int) -> Unit)? = null,
    pageSelected: ((Int) -> Unit)? = null,

    ) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            pageScrolled?.invoke(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            pageSelected?.invoke(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            pageStateChange?.invoke(state)
        }

    })
}