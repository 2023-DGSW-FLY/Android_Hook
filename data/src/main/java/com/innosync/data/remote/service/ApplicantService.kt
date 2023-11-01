package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.mybox.ApplicantResponse
import com.innosync.data.util.Env
import retrofit2.http.GET
import retrofit2.http.Path

interface ApplicantService {
    @GET("${Env.MyBox.get}/{id}/supports")
    suspend fun get(
        @Path("id") id: Int
    ): BaseResponse<List<ApplicantResponse>>

}