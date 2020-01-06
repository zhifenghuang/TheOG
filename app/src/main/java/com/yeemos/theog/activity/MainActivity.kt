package com.yeemos.theog.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.common.lib.activity.BaseActivity
import com.common.lib.utils.Constants
import com.yeemos.theog.R
import com.yeemos.theog.contract.MainContract
import com.yeemos.theog.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = Constants.APP_MAIN)
class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    var mFragments: ArrayList<Fragment> = ArrayList(4);
    var mCurrentFragment: Fragment? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreated(savedInstanceState: Bundle?) {
//        val count = ll_navigator.childCount
//        var itemView: View
//        for (i in 0 until count) {
//            itemView = ll_navigator.getChildAt(i);
//            itemView.setTag(i);
//            itemView.setOnClickListener {
//                val tag: Int = it.tag as Int
//                switchFragment(mFragments.get(tag))
//                resetBottom(tag)
//            }
//        }
//        initFragments()
//        switchFragment(mFragments.get(0))
//        resetBottom(0)
    }

    fun initFragments() {
        mFragments.add(ARouter.getInstance().build("/user/DiscoverFragment").navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build("/user/UserCenterFragment").navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build("/user/DiscoverFragment").navigation() as Fragment)
        mFragments.add(ARouter.getInstance().build("/user/UserCenterFragment").navigation() as Fragment)
    }

    fun resetBottom(index: Int) {
        val count = ll_navigator.childCount
        var itemView: LinearLayout
        var isEqual: Boolean
        for (i in 0 until count) {
            itemView = ll_navigator.getChildAt(i) as LinearLayout
            isEqual = (i == index)
            (itemView.getChildAt(0) as ImageView).isSelected = isEqual
            (itemView.getChildAt(1) as TextView).setTextColor(
                ContextCompat.getColor(
                    this,
                    if (isEqual) {
                        R.color.color_dabc86
                    } else {
                        R.color.color_979797
                    }
                )
            )
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
