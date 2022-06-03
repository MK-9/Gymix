package com.gymix.common.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.util.rangeTo
import com.gymix.common.R
import com.gymix.common.utils.UiUtils.convertToDp

object UiUtils {

    fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    fun isTablet(context: Context): Boolean {
        return context.resources.getBoolean(R.bool.isTablet)
    }

    fun isPortrait(): Boolean {
        return false
    }

    fun getItemCountForHorizontalRcv(context: Context): Int {
        val screenWidthPx = getScreenWidth(context)
        val screenWidthDp = screenWidthPx.convertToDp(context).toInt()
        return when {
            screenWidthDp <= 400 -> 3
            screenWidthDp in 400 until 600 -> 4
            else -> 6
        }
    }

    fun getItemCountForGridRcv(context: Context): Int {
        val screenWidthPx = getScreenWidth(context)
        val screenWidthDp = screenWidthPx.convertToDp(context).toInt()
        return when {
            screenWidthDp <= 400 -> 3
            screenWidthDp in 400 until 600 -> 3
            else -> 4
        }
    }

    fun Int.convertToPx(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return this * displayMetrics.density
    }

    fun Int.convertToDp(context: Context): Float {
        val displayMetrics = context.resources.displayMetrics
        return this / displayMetrics.density
    }


}