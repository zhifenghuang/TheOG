package com.yeemos.theog.presenter

import com.common.lib.mvp.BasePresenter
import com.yeemos.theog.contract.MainContract


class MainPresenter(rootView: MainContract.View) : BasePresenter<MainContract.View>(rootView), MainContract.Presenter {

    override fun getUserInfo() {

    }
}