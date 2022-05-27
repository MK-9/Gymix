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
    val gridPadding = largePadding
//    val gridPadding = (smallPadding + largePadding) / 2

    private var item_count_type_1 = 0
    private var item_count_type_2 = 0

    fun configType1ItemWidth(): Float {
        item_count_type_1 = UiUtils.getItemCountForHorizontalRcv(context)
        val x =
            UiUtils.getScreenWidth(context) - (item_count_type_1 * 2f * smallPadding) - largePadding
        return x / (item_count_type_1 + 0.5f)
    }

    fun configGridItemWidth(): Float {
        item_count_type_2 = UiUtils.getItemCountForGridRcv(context)
        val x = UiUtils.getScreenWidth(context) - (item_count_type_2 * 2f * gridPadding)
        return x / (item_count_type_2)
    }


}