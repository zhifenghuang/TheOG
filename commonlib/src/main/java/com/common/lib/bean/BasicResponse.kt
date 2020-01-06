package com.common.lib.bean

class BasicResponse<T> {

    var code: Int = 0 // 返回的结果标志
        get() = field
        set(value) {
            field = value
        }

    var msg: String? = null
        get() = field
        set(value) {
            field = value
        }

    var result: T? = null
        get() = field
        set(value) {
            field = value
        }

    fun isSuccess(): Boolean {
        return code == 200
    }

}