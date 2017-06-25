package com.github.ggaier.jkmovie.ui.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.ui.adapters.BaseAdapter.BaseViewHolder

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 * Adapter with base loading more footer
 */
class BaseAdapter<T>(
        val mDatas: MutableList<T> = mutableListOf()) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private val VIEW_TYPE_LOAD_MORE = -0xff
        private val VIEW_TYPE_DEFAULT = -0xfe
    }

    private val mLayouts = SparseArray<Int>()

    init {
        mLayouts.put(VIEW_TYPE_LOAD_MORE, R.layout.list_item_load_more)
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {

    }

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