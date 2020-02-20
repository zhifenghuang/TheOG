package com.common.lib.http

import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import kotlin.collections.HashMap

class OkHttpManager private constructor() {

    private val TAG: String = "OkHttpManager"
    private var mOkHttpClient: OkHttpClient? = null

    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    init {
        mOkHttpClient = OkHttpClient()
    }

    companion object {
        @Volatile
        private var instance: OkHttpManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: OkHttpManager().also { instance = it }
            }
    }

    fun post(url: String, map: HashMap<String, Any>, callback: Callback) {
        val body: RequestBody = RequestBody.create(JSON, Gson().toJson(map));
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        val call: Call = mOkHttpClient!!.newCall(request)
        call.enqueue(callback)
    }

}