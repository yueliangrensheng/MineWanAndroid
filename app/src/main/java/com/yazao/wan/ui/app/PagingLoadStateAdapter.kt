package com.yazao.wan.ui.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yazao.wan.R
import com.yazao.wan.databinding.RecyclerLoadFooterBinding

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/10/12
 */

class PagingLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PagingLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {

        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingLoadStateViewHolder {

        return PagingLoadStateViewHolder.create(parent, retry)
    }

}

class PagingLoadStateViewHolder(
    private val binding: RecyclerLoadFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryLoadData.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.loadMore.isVisible = loadState is LoadState.Loading
        binding.retryLoadData.isVisible = loadState is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PagingLoadStateViewHolder {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_load_footer, parent, false)

            return PagingLoadStateViewHolder(
                RecyclerLoadFooterBinding.bind(view), retry
            )
        }
    }
}
