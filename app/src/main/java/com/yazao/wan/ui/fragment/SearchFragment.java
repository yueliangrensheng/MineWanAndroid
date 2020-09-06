package com.yazao.wan.ui.fragment;

import com.yazao.lib.xlog.Log;
import com.yazao.wan.R;
import com.yazao.wan.databinding.FragmentSearchBinding;
import com.yazao.wan.base.BaseFragment;

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/9/2
 */
public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_search;
    }


    @Override
    protected void requestNetData() {

    }

    @Override
    protected void initViewsAndEvents() {
        Log.i(this.getClass().getCanonicalName());
    }
}
