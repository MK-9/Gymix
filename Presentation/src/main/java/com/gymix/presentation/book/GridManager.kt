package com.gymix.presentation.book

import android.content.Context
import com.gymix.common.utils.UiUtils
import com.gymix.common.utils.UiUtils.convertToDp
import com.gymix.presentation.R

class GridManager(val context: Context) {

    val smallPadding = context.resources.getDimensionPixelSize(R.dimen.small_100)
    val largePadding = context.resources.getDimensionPixelSize(R.dimen.normal_100)

    //
    val innerPadding = context.resources.getDimensionPixelOffset(R.dimen.normal_100)
    val outerPadding = context.resources.getDimensionPixelOffset(R.dimen.normal_250)

    fun getBoxItemWidth(): Float {
        val itemCount = getItemCountForHorizontalRcv(context)
        val x =
            UiUtils.getScreenWidth(context) - (itemCount * 2f * smallPadding) - largePadding
        return x / (itemCount + 0.5f)
    }

    fun getGridItemWidth(): Int {
        val itemCount = getSpanCountForGridRcv(context)
        return (UiUtils.getScreenWidth(context) - (2 * outerPadding) - (itemCount - 1) * (innerPadding)) / itemCount
    }

    fun getGridSpanWidth(): Int =
        UiUtils.getScreenWidth(context) / getSpanCountForGridRcv(context)

    fun getSpanCountForGridRcv(context: Context): Int {
        val screenWidthPx = UiUtils.getScreenWidth(context)
        val screenWidthDp = screenWidthPx.convertToDp(context).toInt()
        return when {
            screenWidthDp <= 400 -> 3
            screenWidthDp in 400 until 600 -> 3
            else -> 4
        }
    }

    fun getItemCountForHorizontalRcv(context: Context): Int {
        val screenWidthPx = UiUtils.getScreenWidth(context)
        val screenWidthDp = screenWidthPx.convertToDp(context).toInt()
        return when {
            screenWidthDp <= 400 -> 3
            screenWidthDp in 400 until 600 -> 4
            else -> 6
        }
    }

}