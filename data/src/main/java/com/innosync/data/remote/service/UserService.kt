package com.innosync.data.remote.service

import com.innosync.data.remote.request.UserGetTheseRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.user.UserResponse
import com.innosync.data.util.Env
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET(Env.User.get)
    suspend fun getMyInfo(

    ): BaseResponse<UserResponse>
}