package com.innosync.data.remote.service

import com.innosync.data.remote.request.UserJoinRequest
import com.innosync.data.remote.request.UserLoginRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {


    @POST("api/v1/users/login")
    suspend fun login (
        @Body body : UserLoginRequest
    ) : BaseResponse<LoginResponse>

    @POST("api/v1/users/join")
    suspend fun join (
        @Body body : UserJoinRequest
    ): Call<Unit>

}