package com.common.lib.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.common.lib.mvp.IPresenter

abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), View.OnClickListener {

    protected var mPresenter: P? = null

    protected abstract fun getLayoutId(): Int

    protected abstract fun onCreated(savedInstanceState: Bundle?)

    protected abstract fun getPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(getLayoutId())
        mPresenter = getPresenter();
        onCreated(savedInstanceState)
    }

    protected fun setViewsOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    protected fun setTextColor(tv: TextView, clorId: Int) {
        tv.setTextColor(ContextCompat.getColor(this, clorId))
    }

    protected fun setViewVisible(vararg views: View) {
        for (view in views) {
            view.setVisibility(View.VISIBLE)
        }
    }

    protected fun setViewGone(vararg views: View) {
        for (view in views) {
            view.setVisibility(View.GONE)
        }
    }

    protected fun setViewInvisible(vararg views: View) {
        for (view in views) {
            view.setVisibility(View.INVISIBLE)
        }
    }

    protected fun goPager(cls: Class<*>) {
        goPager(cls, null)
    }

    protected fun goPager(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 点后退按钮触发
     *
     * @param view
     */
    fun onBackClick(view: View) {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()

        //释放资源
        mPresenter?.onDestroy()
        mPresenter = null
    }

    fun showLoading() {

    }

    fun hideLoading() {

    }
}