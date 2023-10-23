package com.innosync.data.remote.service

import com.innosync.data.remote.request.UserGetTheseRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.user.UserNameResponse
import com.innosync.data.util.Env
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatService{

    @POST(Env.User.getThese)
    suspend fun getThese(
        @Body users: UserGetTheseRequest
    ): BaseResponse<Map<String, String>>

    @GET("${Env.User.get}/{id}")
    suspend fun getUserName(
        @Path("id") userId: String
    ): BaseResponse<UserNameResponse>

}