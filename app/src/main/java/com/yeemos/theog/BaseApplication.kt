package com.yeemos.theog

import android.app.Application
import com.common.lib.manager.ConfigurationManager

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ConfigurationManager.getInstance().setContext(this)
    }
}