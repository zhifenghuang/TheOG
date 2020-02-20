package com.common.lib.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.common.lib.mvp.IPresenter

abstract class BaseFragment<P : IPresenter> : Fragment(), View.OnClickListener {

    protected var mPresenter: P? = null

    protected abstract fun getPresenter(): P

    protected abstract fun getLayoutId(): Int

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

    protected fun setViewsOnClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    protected fun setTextColor(tv: TextView, clorId: Int) {
        tv.setTextColor(ContextCompat.getColor(activity!!, clorId))
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

    protected fun setTextByServerKey(tv: TextView, serverKey: String) {
        tv.text = getTextByKey(serverKey)
    }

    protected fun goPager(cls: Class<*>) {
        goPager(cls, null)
    }

    protected fun goPager(cls: Class<*>, bundle: Bundle?) {
        val intent = Intent(activity, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
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
}