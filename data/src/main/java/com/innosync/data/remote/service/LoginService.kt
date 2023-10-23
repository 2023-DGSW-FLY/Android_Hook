package com.innosync.data.remote.service

import com.innosync.data.remote.request.UserJoinRequest
import com.innosync.data.remote.request.UserLoginRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.JoinResponse
import com.innosync.data.remote.response.login.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface LoginService {


    @POST("api/v1/users/login")
    suspend fun login (
        @Body body : UserLoginRequest
    ) : BaseResponse<LoginResponse>

    @Multipart
    @POST("api/v1/users/join")
    suspend fun join (
        @Part image: MultipartBody.Part,
        @Part("data") body : UserJoinRequest
    ): JoinResponse

}