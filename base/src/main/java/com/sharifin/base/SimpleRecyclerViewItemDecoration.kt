package com.sharifin.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleRecyclerViewItemDecoration(
        val left: Int = 0,
        val right: Int = 0,
        val top: Int = 0,
        val bottom: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = bottom
        outRect.top = top
        outRect.left = left
        outRect.right = right
    }
}