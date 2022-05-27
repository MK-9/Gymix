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
    private var item_count_type_1 = 0
    private val item_count_type_2 = 1


    fun configType1ItemWidth(): Float {
        val screenWidth = UiUtils.getScreenWidth(context)
        item_count_type_1 = UiUtils.getItemCountForHorizontalRcv(context)

        val x = screenWidth -
                (item_count_type_1 * 2f * context.resources.getDimensionPixelSize(R.dimen.small_100)) -
                (context.resources.getDimensionPixelSize(R.dimen.normal_100))

        return x / (item_count_type_1 + 0.5f)
    }



}