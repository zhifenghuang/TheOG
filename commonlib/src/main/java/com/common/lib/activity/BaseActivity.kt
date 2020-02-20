package com.common.lib.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.common.lib.mvp.IPresenter
import com.common.lib.utils.PermissionUtils
import java.util.ArrayList

abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), View.OnClickListener {

    protected var mPresenter: P? = null

    private var mPermissionCallBack: PermissionUtils.PermissionCallBack? = null

    protected abstract fun getLayoutId(): Int

    protected abstract fun onCreated(savedInstanceState: Bundle?)

    protected abstract fun getPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mPresenter = getPresenter();
        onCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermission(
                0,
                null,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
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
        val intent = Intent(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    fun requestPermission(
        permissionReqCode: Int,
        callback: PermissionUtils.PermissionCallBack?,
        vararg permissions: String
    ) {
        mPermissionCallBack = callback
        var uncheckPermissions: ArrayList<String>? = null
        for (permission in permissions) {
            if (!PermissionUtils.isGrantPermission(this, permission)) {
                //进行权限请求
                if (uncheckPermissions == null) {
                    uncheckPermissions = ArrayList()
                }
                uncheckPermissions.add(permission)
            }
        }
        if (uncheckPermissions != null && !uncheckPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                uncheckPermissions.toTypedArray(),
                permissionReqCode
            )
        } else {
            mPermissionCallBack?.onSuccess()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var isAllGranted = true
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false
                break
            }
        }
        if (isAllGranted) {
            mPermissionCallBack?.onSuccess()
        } else {
            mPermissionCallBack?.onFailure()
        }
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