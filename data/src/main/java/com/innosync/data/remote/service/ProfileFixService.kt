package com.innosync.data.remote.service

import com.innosync.data.remote.request.UserJoinRequest
import com.innosync.data.remote.request.profilefix.ProfileFixRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface ProfileFixService {


    @PUT("api/v1/users/fix/user")
    suspend fun fix(
        @Body body : ProfileFixRequest
    )

    @Multipart
    @PUT("api/v1/users/fix/image")
    suspend fun image(
        @Part image: MultipartBody.Part?
    )


}