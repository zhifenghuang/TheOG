package com.common.lib.mvp

import com.common.lib.mvp.BasePresenter
import com.common.lib.mvp.EmptyContract


class EmptyPresenter(rootView: EmptyContract.View) : BasePresenter<EmptyContract.View>(rootView), EmptyContract.Presenter {


}