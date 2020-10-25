package com.yazao.wan.listener

import android.view.View

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/10/10
 */
fun interface OnItemLongClickListener {
    fun onItemLongClick(position: Int, view: View?)
}