package com.yeemos.theog.presenter

import com.common.lib.mvp.BasePresenter
import com.yeemos.theog.contract.ChooseContract
import com.yeemos.theog.contract.StoryContract


class ChoosePresenter(rootView: ChooseContract.View) : BasePresenter<ChooseContract.View>(rootView), StoryContract.Presenter {


}