package com.common.lib.utils

import android.content.Context
import android.content.res.Resources

class BaseUtils {
    companion object StaticParams {
        /**
         * dp转px
         *
         * @param context
         * @param dipValue
         * @return
         */
        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        /**
         * Return the status bar's height.
         *
         * @return the status bar's height
         */
        fun getStatusBarHeight(resources: Resources): Int {
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

    }
}