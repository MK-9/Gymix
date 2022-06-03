package com.gymix.presentation.book

import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowManager
import android.view.WindowMetrics
import com.gymix.common.utils.UiUtils
import com.gymix.presentation.R

class GridManager(val context: Context) {

    val smallPadding = context.resources.getDimensionPixelSize(R.dimen.small_100)
    val largePadding = context.resources.getDimensionPixelSize(R.dimen.normal_100)

    //
    val innerPadding = context.resources.getDimensionPixelOffset(R.dimen.normal_100)
    val startPadding = context.resources.getDimensionPixelOffset(R.dimen.normal_250)

    fun getBoxItemWidth(): Float {
        val itemCount = UiUtils.getItemCountForHorizontalRcv(context)
        val x =
            UiUtils.getScreenWidth(context) - (itemCount * 2f * smallPadding) - largePadding
        return x / (itemCount + 0.5f)
    }

    fun getGridItemWidth(): Int {
        val itemCount = UiUtils.getItemCountForGridRcv(context)
        return (UiUtils.getScreenWidth(context) - (2 * startPadding) - (itemCount - 1) * (innerPadding)) / itemCount
    }

    fun getSpanWidth(): Int =
        UiUtils.getScreenWidth(context) / UiUtils.getItemCountForGridRcv(context)

}