package com.github.ggaier.jkmovie.ui.adapters

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
class BaseAdapter<T>(
        val mDatas: MutableList<T> = mutableListOf()) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private val LOAD_MORE_THREADHOLD = 5
        private val VIEW_TYPE_LOAD_MORE = -0xff
        private val VIEW_TYPE_DEFAULT = -0xfe
    }

    private val mLayouts = SparseArray<Int>()

    init {
        mLayouts.put(VIEW_TYPE_LOAD_MORE, R.layout.list_item_load_more)
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        showLoadMore(holder, position)
    }

    private var mLoadMoreComplete: Boolean = false
    private var mLoadMoreFailed: Boolean = false

    private fun showLoadMore(holder: BaseViewHolder?, position: Int) {
        if (mDatas.isEmpty()) {
            holder?.itemView?.load_more_progressbar?.visibility = View.GONE
            return
        }
        if (position == mDatas.size - 1) {
            holder?.itemView?.load_more_progressbar?.visibility = if (shouldShowLoadMore())
                View.VISIBLE else View.GONE
        }
    }

    private fun shouldShowLoadMore(): Boolean = !mLoadMoreComplete || mLoadMoreFailed


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
                LayoutInflater.from(parent!!.context).inflate(mLayouts.get(viewType), parent,
                        false))
    }

    override fun getItemCount(): Int {
        return mDatas.size + 1
    }


    class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

}