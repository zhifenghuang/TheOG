package com.common.lib.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.common.lib.activity.BaseActivity
import com.common.lib.mvp.IPresenter
import com.common.lib.utils.BaseUtils

abstract class BaseFragment<P : IPresenter> : Fragment(), View.OnClickListener {

    protected var mPresenter: P? = null

    protected abstract fun getPresenter(): P

    protected abstract fun getLayoutId(): Int

    protected abstract fun updateUI()

    /**
     * fragment的View创建好后调用
     */
    protected abstract fun initView(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = getPresenter();
        initView(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    protected fun setViewsOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    protected fun setTextColor(tv: TextView, clorId: Int) {
        tv.setTextColor(ContextCompat.getColor(activity!!, clorId))
    }

    protected fun setTextByServerKey(tv: TextView, serverKey: String) {
        tv.text = getTextByKey(serverKey)
    }

    protected fun setViewVisible(vararg views: View) {
        for (view in views) {
            view.visibility = View.VISIBLE
        }
    }

    protected fun setViewGone(vararg views: View) {
        for (view in views) {
            view.visibility = View.GONE
        }
    }

    protected fun setViewInvisible(vararg views: View) {
        for (view in views) {
            view.visibility = View.INVISIBLE
        }
    }

    protected fun goPager(cls: Class<*>) {
        goPager(cls, null)
    }

    protected fun goPager(cls: Class<*>, bundle: Bundle?) {
        (activity as BaseActivity<*>).goPager(cls, bundle)
    }

    fun showLoading() {
    }

    fun hideLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()

        //释放资源
        mPresenter?.onDestroy()
        mPresenter = null
    }

    fun getTextByKey(key: String): String {
        var value = ""
        try {
            val stringId = getResources().getIdentifier(
                key,
                "string", activity?.packageName
            )
            if (stringId > 0) {
                value = getResources().getString(stringId)// 取出配置的string文件中的默认值
            }
        } catch (e: Exception) {
            value = ""
        }
        return value
    }

    protected open fun setTopStatusBarStyle(topView: View) {
        topView.setPadding(0, BaseUtils.getStatusBarHeight(resources) + topView.paddingTop, 0, 0)
    }
}