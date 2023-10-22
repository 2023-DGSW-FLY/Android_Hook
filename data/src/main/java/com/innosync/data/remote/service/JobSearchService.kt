package com.innosync.data.remote.service

import com.innosync.data.remote.request.jobsearch.JobSearchInsertRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.TestResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.data.util.Env
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JobSearchService {

    @GET(Env.JobSearch.get)
    suspend fun get(

    ): BaseResponse<List<JobSearchResponse>>

    @GET(Env.JobSearch.get)
    suspend fun get(
        @Query("cnt") cnt: Int
    ): BaseResponse<List<JobSearchResponse>>

    @GET(Env.JobSearch.getStack)
    suspend fun getStack(
        @Query("job") job: String
    ): BaseResponse<List<JobSearchResponse>>

    @POST(Env.JobSearch.insert)
    suspend fun insert(
        @Body body: JobSearchInsertRequest
    ): TestResponse
}