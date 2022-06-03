package com.gymix.presentation.book

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gymix.common.utils.UiUtils
import kotlin.math.ceil

class GridBookItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    private var itemCount = UiUtils.getItemCountForGridRcv(context)

    private val gridManager by lazy { createGridManager() }
    private val itemWidth = gridManager.getGridItemWidth()
    private val spanWidth = gridManager.getSpanWidth()

    private val paddingsArray = Array(itemCount) { IntArray(2) }

    init {
        configItemPadding()
    }

    private fun createGridManager() = GridManager(context)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val rowCount = ceil(parent.adapter!!.itemCount.toDouble() / itemCount).toInt()
        val position = parent.getChildAdapterPosition(view)
        //set top and bottom padding for first and bottom row
        when {
            //first row
            position < itemCount -> {
                outRect.top = gridManager.largePadding
                outRect.bottom = gridManager.smallPadding
            }
            //last row
            position / itemCount == rowCount - 1 -> {
                outRect.top = gridManager.smallPadding
                outRect.bottom = gridManager.largePadding
            }
            else -> {
                outRect.top = gridManager.smallPadding
                outRect.bottom = gridManager.smallPadding
            }
        }

        outRect.left = paddingsArray[position % itemCount][0]
        outRect.right = paddingsArray[position % itemCount][1]
    }


    private fun configItemPadding() {
        for (j in 0 until itemCount) {
            fillRightPadding(j)
        }
    }

    private fun fillRightPadding(i: Int): Int {
        if (i == 0) {
            val leftPadding = gridManager.startPadding
            val rightPadding = spanWidth - itemWidth - gridManager.startPadding
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