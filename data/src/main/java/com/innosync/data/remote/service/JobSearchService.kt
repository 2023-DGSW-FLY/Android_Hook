package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.data.util.Env
import retrofit2.http.GET
import retrofit2.http.Query

interface JobSearchService {

    @GET(Env.JobSearch.get)
    suspend fun get(

    ): BaseResponse<List<JobSearchResponse>>

    @GET(Env.JobSearch.get)
    suspend fun get(
        @Query("cnt") cnt: Int
    ): BaseResponse<List<JobSearchResponse>>
}