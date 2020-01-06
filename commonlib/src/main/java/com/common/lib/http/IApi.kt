package com.common.lib.http

import com.common.lib.bean.BasicResponse
import com.common.lib.bean.UserBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface IApi {


    @Multipart
    @POST("api/v1/passport/login")
    fun login(@Part("loginAccount") loginAccount:RequestBody ,
              @Part("loginPassword") loginPassword: RequestBody
    ): Observable<BasicResponse<UserBean>>
}