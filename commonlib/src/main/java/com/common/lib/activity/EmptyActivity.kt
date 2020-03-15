package com.common.lib.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.common.lib.R
import com.common.lib.fragment.BaseFragment
import com.common.lib.mvp.EmptyContract
import com.common.lib.mvp.EmptyPresenter

class EmptyActivity : BaseActivity<EmptyPresenter>(), EmptyContract.View {

    private var mCanBackFinish = true

    override fun getLayoutId(): Int {
        return R.layout.activity_empty
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        mCanBackFinish = true
        val intent = intent
        val fragmentName = intent.getStringExtra("FRAGMENT_NAME")
        val fragment = Fragment.instantiate(
            this,
            fragmentName
        ) as BaseFragment<*>
        val b = intent.extras
        fragment.setArguments(b)
        val ft =
            supportFragmentManager.beginTransaction()
        ft.add(R.id.container, fragment, fragmentName)
        ft.addToBackStack(null)
        ft.commitAllowingStateLoss()
    }

    override fun updateUI() {
    }

    override fun getPresenter(): EmptyPresenter {
        return EmptyPresenter(this)
    }

    override fun onClick(v: View?) {
    }

    fun setCanBackFinish(canBackFinish: Boolean) {
        mCanBackFinish = canBackFinish
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!mCanBackFinish) {
                return false
            }
            return false
        }
        return super.onKeyDown(keyCode, event)
    }

}