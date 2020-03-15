package com.yeemos.theog.fragment

import android.os.Bundle
import android.view.View
import com.common.lib.fragment.BaseFragment
import com.yeemos.theog.R
import com.yeemos.theog.contract.StoryContract
import com.yeemos.theog.presenter.StoryPresenter

class StoryFragment : BaseFragment<StoryPresenter>(),StoryContract.View {
    override fun getPresenter(): StoryPresenter {
        return StoryPresenter(this);
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_story
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
    }

    override fun onClick(v: View?) {

    }

    override fun updateUI() {

    }
}