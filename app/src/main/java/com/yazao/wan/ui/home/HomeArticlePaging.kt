package com.yazao.wan.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.yazao.wan.R
import com.yazao.wan.base.BasePagingDataAdapter
import com.yazao.wan.base.BaseViewHolder
import com.yazao.wan.data.db.HomeArticleDetail
import com.yazao.wan.databinding.ItemRecyclerHomeArticleBinding
import com.yazao.wan.ext.renderHtml


class HomeArticlePagingAdapter :
    BasePagingDataAdapter<HomeArticleDetail, ItemRecyclerHomeArticleBinding>(DIFF_CALLBACK) {


    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HomeArticleDetail>() {

            override fun areItemsTheSame(
                oldItem: HomeArticleDetail,
                newItem: HomeArticleDetail
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: HomeArticleDetail,
                newItem: HomeArticleDetail
            ): Boolean {
                return oldItem == newItem
            }
        }

    }

    override fun setVariable(
        data: HomeArticleDetail,
        position: Int,
        holder: BaseViewHolder<ItemRecyclerHomeArticleBinding>
    ) {
        holder.binding.detail = data
        holder.binding.title = data.title.renderHtml()
    }

    override fun getLayoutId(): Int = R.layout.item_recycler_home_article

}
