package com.gymix.presentation.book

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gymix.common.utils.UiUtils
import com.gymix.presentation.R
import kotlin.math.ceil

class GridBookItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    private val smallPadding: Int = context.resources.getDimensionPixelOffset(R.dimen.small_100)
    private val largePadding: Int = context.resources.getDimensionPixelOffset(R.dimen.normal_100)

    private val gridManager by lazy { createGridManager() }
    private val itemCount = UiUtils.getItemCountForGridRcv(context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val rowCount = ceil(parent.adapter!!.itemCount.toDouble() / itemCount).toInt()

        //set top and bottom padding for first and bottom row
        when {
            //first row
            parent.getChildAdapterPosition(view) < itemCount -> {
                outRect.top = gridManager.gridPadding
                outRect.bottom = gridManager.smallPadding
            }
            //last row
            parent.getChildAdapterPosition(view) / itemCount == rowCount - 1 -> {
                outRect.top = gridManager.smallPadding
                outRect.bottom = gridManager.gridPadding
            }
            else -> {
                outRect.top = gridManager.smallPadding
                outRect.bottom = gridManager.smallPadding
            }
        }

        outRect.left = gridManager.gridPadding
        outRect.right = gridManager.gridPadding
    }

    private fun createGridManager() = GridManager(context)
}