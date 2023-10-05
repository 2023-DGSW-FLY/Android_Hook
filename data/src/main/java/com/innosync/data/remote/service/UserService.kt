package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.user.UserResponse
import com.innosync.data.util.Env
import retrofit2.http.GET

interface UserService {

    @GET(Env.My.get)
    suspend fun getMyInfo(

    ): BaseResponse<UserResponse>
}