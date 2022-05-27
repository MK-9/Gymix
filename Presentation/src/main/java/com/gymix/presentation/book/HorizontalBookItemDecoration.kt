package com.gymix.presentation.book

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gymix.presentation.R

class HorizontalBookItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val smallPadding: Int = context.resources.getDimensionPixelOffset(R.dimen.small_100)
    private val largePadding: Int = context.resources.getDimensionPixelOffset(R.dimen.normal_100)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.top = smallPadding
                outRect.bottom = smallPadding
                outRect.left = smallPadding
                outRect.right = largePadding
            }
            parent.adapter!!.itemCount - 1 -> {
                outRect.top = smallPadding
                outRect.bottom = smallPadding
                outRect.left = largePadding
                outRect.right = smallPadding
            }
            else -> {
                outRect.top = smallPadding
                outRect.bottom = smallPadding
                outRect.left = smallPadding
                outRect.right = smallPadding
            }
        }
    }
}