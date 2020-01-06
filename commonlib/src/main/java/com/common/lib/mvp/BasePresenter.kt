package com.common.lib.mvp

import android.util.Log

open class BasePresenter<V : IView>(rootView: V) : IPresenter {

    protected var mRootView: V? = null

    init {
        mRootView = rootView;
        onStart()
    }

    override fun onStart() {
        Log.e("BasePresenter","onStart")
    }

    override fun onDestroy() {
        Log.e("BasePresenter","onDestroy")
    }
}