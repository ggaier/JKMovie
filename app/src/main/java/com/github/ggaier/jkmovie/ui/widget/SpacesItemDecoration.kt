package com.github.ggaier.jkmovie.ui.widget

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class SpacesItemDecoration internal constructor(private val mSpace: Int) :
        RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?,
                                state: RecyclerView.State?) {
        outRect!!.left = mSpace
        outRect!!.right = mSpace
        outRect!!.bottom = mSpace
        if (parent?.getChildLayoutPosition(view) ?: -1 == 0) {
            outRect!!.top = mSpace
        } else {
            outRect!!.top = 0
        }
    }

}