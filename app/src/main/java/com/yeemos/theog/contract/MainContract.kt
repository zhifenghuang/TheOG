package com.yeemos.theog.contract

import com.common.lib.mvp.IPresenter
import com.common.lib.mvp.IView


interface MainContract {

    interface View : IView {

        fun getUserSuccess()

        fun getUserFailed()
    }

    interface Presenter : IPresenter {
        fun getUserInfo()
    }
}