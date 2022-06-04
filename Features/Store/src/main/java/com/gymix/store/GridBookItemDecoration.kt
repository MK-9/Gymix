package com.gymix.store

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gymix.common.utils.GridManager
import kotlin.math.ceil

class GridBookItemDecoration(context: Context, private val gridManager: GridManager) :
    RecyclerView.ItemDecoration() {

    private var spanCount = gridManager.getSpanCountForGridRcv(context)
    private val itemWidth = gridManager.getGridItemWidth()
    private val spanWidth = gridManager.getGridSpanWidth()

    private val paddingsArray = Array(spanCount) { IntArray(2) }

    init {
        configItemPadding()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val rowCount = ceil(parent.adapter!!.itemCount.toDouble() / spanCount).toInt()
        val position = parent.getChildAdapterPosition(view)
        //set top and bottom padding for first and bottom row
        when {
            //first row
            position < spanCount -> {
                outRect.top = gridManager.largePadding
                outRect.bottom = gridManager.smallPadding
            }
            //last row
            position / spanCount == rowCount - 1 -> {
                outRect.top = gridManager.smallPadding
                outRect.bottom = gridManager.largePadding
            }
            else -> {
                outRect.top = gridManager.smallPadding
                outRect.bottom = gridManager.smallPadding
            }
        }

        outRect.left = paddingsArray[position % spanCount][0]
        outRect.right = paddingsArray[position % spanCount][1]
    }


    private fun configItemPadding() {
        for (i in 0 until spanCount) {
            fillRightPadding(i)
        }
    }

    private fun fillRightPadding(i: Int): Int {
        if (i == 0) {
            val leftPadding = gridManager.outerPadding
            val rightPadding = spanWidth - itemWidth - gridManager.outerPadding
            paddingsArray[0][0] = leftPadding
            paddingsArray[0][1] = rightPadding
            return rightPadding
        }

        val leftPadding = gridManager.innerPadding - fillRightPadding(i - 1)
        val rightPadding = spanWidth - itemWidth - leftPadding
        paddingsArray[i][0] = leftPadding
        paddingsArray[i][1] = rightPadding
        return rightPadding
    }
}