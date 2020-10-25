package com.yazao.wan.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.yazao.wan.listener.OnItemClickListener
import com.yazao.wan.listener.OnItemLongClickListener

abstract class BasePagingDataAdapter<T : Any, VB : ViewDataBinding>(val callback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BaseViewHolder<VB>>(callback) {


    var itemClickListener: OnItemClickListener? = null

    var itemLongClickListener: OnItemLongClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {

        val data = getItem(position) ?: return

        setVariable(data, position, holder)

        holder.binding.executePendingBindings()

        holder.binding.root.run {
            setOnClickListener { itemClickListener?.onItemClick(position, it) }

            setOnLongClickListener {
                itemLongClickListener?.onItemLongClick(position, it)
                false
            }
        }

    }

    /**
     * 对应 position 的数据
     */
    open fun getItemData(position: Int): T? = getItem(position)

    abstract fun setVariable(data: T, position: Int, holder: BaseViewHolder<VB>)

    abstract fun getLayoutId(): Int
}
