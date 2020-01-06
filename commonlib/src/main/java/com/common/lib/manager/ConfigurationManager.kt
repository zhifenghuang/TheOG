package com.common.lib.manager

import android.content.Context

class ConfigurationManager private constructor() {

    private var mContext: Context? = null

    companion object {
        @Volatile
        private var instance: ConfigurationManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ConfigurationManager().also { instance = it }
            }
    }

    fun setContext(context: Context?) {
        mContext = context;
    }

    fun getContext() =
        mContext
}