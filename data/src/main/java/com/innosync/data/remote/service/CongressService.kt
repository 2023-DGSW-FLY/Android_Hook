package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.congress.CongressResponse
import retrofit2.http.GET

interface CongressService {

    @GET("/contest")
    suspend fun getContest(

    ): BaseResponse<List<CongressResponse>>
}