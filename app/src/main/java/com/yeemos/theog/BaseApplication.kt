package com.yeemos.theog

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.common.lib.manager.ConfigurationManager

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ConfigurationManager.getInstance().setContext(this)
        ARouter.init(this);
    }
}