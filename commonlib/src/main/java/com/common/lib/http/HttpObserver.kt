package com.common.lib.http


import android.os.Handler
import android.os.Looper
import android.os.Message
import com.common.lib.bean.BasicResponse
import com.common.lib.mvp.IView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HttpObserver<T : BasicResponse<Data>, Data> : Observer<T> {

    private var isShowLoading: Boolean? = true
    private var view: IView? = null
    private var listener: HttpListener<Data>? = null

    val SHOW_LOADING = 1
    val HIDE_LOADING = 2

    constructor(listener: HttpListener<Data>) : this(false, null, listener)

    constructor(view: IView?, listener: HttpListener<Data>) : this(true, view, listener)

    constructor(isShowLoading: Boolean, view: IView?, listener: HttpListener<Data>) {
        this.isShowLoading = isShowLoading;
        this.view = view;
        this.listener = listener
    }

    override fun onComplete() {
        hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
        showLoading()
    }

    override fun onNext(t: T) {
        if (t.isSuccess()) {
            listener?.onSuccess(t.result!!)
        } else {
            listener?.dataError(t.code, t.msg!!)
        }
    }

    override fun onError(e: Throwable) {
        hideLoading()
        listener?.connectError(e)
    }

    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SHOW_LOADING -> {
                    view?.showLoading()
                }
                HIDE_LOADING -> {
                    view?.hideLoading()
                }
            }
        }
    }

    fun showLoading() {
        if (isShowLoading!! && view != null) {
            mHandler.obtainMessage(SHOW_LOADING).sendToTarget()
        }
    }

    fun hideLoading() {
        if (isShowLoading!! && view != null) {
            mHandler.obtainMessage(HIDE_LOADING).sendToTarget()
        }
    }

}