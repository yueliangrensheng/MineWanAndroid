package com.yazao.wan.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * viewpager + fragment adapter
 */
class MainFragmentPagerAdapter(
    fm: FragmentManager,
    fragments: ArrayList<out Fragment>,
    titles: Array<String>? = null
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var mFragments = fragments
    private var mTitles = titles

    init {
        if (mTitles.isNullOrEmpty()) {
            mTitles = Array(fragments.size) { "" }
        }
    }

    override fun getCount(): Int = mFragments.size

    override fun getItem(position: Int): Fragment = mFragments[position]

    override fun getPageTitle(position: Int): CharSequence? =
        if (mTitles.isNullOrEmpty()) super.getPageTitle(position) else mTitles!![position]
}
