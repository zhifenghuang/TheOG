package com.common.lib.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

class PermissionUtils {

    companion object{
        /**
         * @param context
         * @param permission
         * @return
         */
        fun isGrantPermission(context: Context, permission: String): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        }
    }


    /**
     * 回调
     */
    interface PermissionCallBack {
        fun onSuccess()

        fun onFailure()
    }
}