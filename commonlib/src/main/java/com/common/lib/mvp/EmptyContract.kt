package com.common.lib.mvp

import com.common.lib.mvp.IPresenter
import com.common.lib.mvp.IView


interface EmptyContract {

    interface View : IView {
    }

    interface Presenter : IPresenter {
    }
}