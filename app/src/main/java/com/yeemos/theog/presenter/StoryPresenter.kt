package com.yeemos.theog.presenter

import com.common.lib.mvp.BasePresenter
import com.yeemos.theog.contract.MainContract
import com.yeemos.theog.contract.StoryContract


class StoryPresenter(rootView: StoryContract.View) : BasePresenter<StoryContract.View>(rootView), StoryContract.Presenter {


}