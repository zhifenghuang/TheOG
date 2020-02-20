package com.yeemos.theog.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.common.lib.activity.BaseActivity
import com.yeemos.theog.R
import com.yeemos.theog.contract.MainContract
import com.yeemos.theog.fragment.StoryFragment
import com.yeemos.theog.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    var mFragments: ArrayList<Fragment> = ArrayList(5);
    var mCurrentFragment: Fragment? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        val count = ll_navigator.childCount
        var itemView: View
        for (i in 0 until count) {
            itemView = ll_navigator.getChildAt(i);
            itemView.setTag(i);
            itemView.setOnClickListener {
                val tag: Int = it.tag as Int
                switchFragment(mFragments.get(tag))
                resetBottom(tag)
            }
        }
        initFragments()
        switchFragment(mFragments.get(0))
        resetBottom(0)
    }

    fun initFragments() {
        mFragments.add(StoryFragment())
        mFragments.add(StoryFragment())
        mFragments.add(StoryFragment())
        mFragments.add(StoryFragment())
        mFragments.add(StoryFragment())
    }

    fun resetBottom(index: Int) {
        val count = ll_navigator.childCount
        for (i in 0 until count) {
            (ll_navigator.getChildAt(i) as ImageView).isSelected = (index == i)
        }
    }

    override fun getPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun onClick(v: View?) {

    }

    override fun getUserSuccess() {

    }

    override fun getUserFailed() {

    }

    /**
     * @param to 马上要切换到的Fragment，一会要显示
     */
    fun switchFragment(to: Fragment) {
        if (mCurrentFragment !== to) {
            val ft = supportFragmentManager.beginTransaction()
            if (!to.isAdded()) {
                if (mCurrentFragment != null) {
                    ft.hide(mCurrentFragment!!)
                }
                ft.add(R.id.container, to).commit()
            } else {
                if (mCurrentFragment != null) {
                    ft.hide(mCurrentFragment!!)
                }
                ft.show(to).commit()
            }
        }
        mCurrentFragment = to
    }
}
