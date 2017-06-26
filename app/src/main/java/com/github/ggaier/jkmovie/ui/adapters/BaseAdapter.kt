package com.github.ggaier.jkmovie.ui.adapters

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.ui.adapters.BaseAdapter.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_load_more.view.*

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 * Adapter with base loading more footer
 */
abstract class BaseAdapter<T>(
        val mDatas: MutableList<T> = mutableListOf(),
        @LayoutRes defaultLayoutId: Int) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private val LOAD_MORE_THREADHOLD = 5
        private val VIEW_TYPE_LOAD_MORE = -0xff
        private val VIEW_TYPE_DEFAULT = -0xfe
    }

    private val mLayouts = SparseArray<Int>()
    private var mLoadMoreComplete: Boolean = false
    private var mLoadMoreFailed: Boolean = false
    lateinit var mOnLoadMoreListener: (BaseAdapter<T>) -> Unit

    init {
        mLayouts.put(VIEW_TYPE_LOAD_MORE, R.layout.list_item_load_more)
        mLayouts.put(VIEW_TYPE_DEFAULT, defaultLayoutId)
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        notifyLoadMoreEvent(position)
        when (getItemViewType(position)) {
            VIEW_TYPE_DEFAULT -> bindDefault(holder, mDatas[position])
            VIEW_TYPE_LOAD_MORE -> showLoadMore(holder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return VIEW_TYPE_LOAD_MORE
        }
        return VIEW_TYPE_DEFAULT
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
                LayoutInflater.from(parent!!.context).inflate(mLayouts.get(viewType), parent,
                        false))
    }

    override fun getItemCount(): Int = mDatas.size + 1

    abstract fun bindDefault(holder: BaseViewHolder?, itemData: T)

    private fun notifyLoadMoreEvent(position: Int) {
        if (position == mDatas.size - LOAD_MORE_THREADHOLD && mOnLoadMoreListener != null) {
            mOnLoadMoreListener(this)
        }
    }

    private fun showLoadMore(holder: BaseViewHolder?) {
        holder?.itemView?.load_more_progressbar?.visibility = if (shouldShowLoadMore())
            View.VISIBLE else View.GONE
    }

    private fun shouldShowLoadMore(): Boolean = !mLoadMoreComplete && mLoadMoreFailed

    fun loadMoreFailed() {
        mLoadMoreFailed = true
        notifyItemChanged(mDatas.size)
    }

    fun loadMoreComplete() {
        mLoadMoreComplete = true
        notifyItemChanged(mDatas.size)
    }

    fun addDatas(list: List<T>) {
        val preSize = mDatas.size
        mDatas.addAll(list)
        notifyItemRangeChanged(preSize, mDatas.size)
    }

    class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}