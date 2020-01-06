package com.common.lib.mvp

interface IPresenter {

    /**
     * 做一些初始化操作
     */
    fun onStart()

    /**
     * 在框架中 { Activity#onDestroy() } 时会默认调用 [IPresenter.onDestroy]
     */
    fun onDestroy()
}