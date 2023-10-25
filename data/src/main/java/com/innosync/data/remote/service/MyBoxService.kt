package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.jobopening.EatResponse
import com.innosync.data.remote.response.jobopening.ExerciseResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.domain.model.JobSearchModel
import retrofit2.http.GET

interface MyBoxService {
    @GET("/hackathon/all")
    suspend fun getHackertonHeader(

    ): BaseResponse<List<HackathonResponse>>

    @GET("/food/get")
    suspend fun getMealHeader(

    ): BaseResponse<List<EatResponse>>

   @GET("/exercise/get")
   suspend fun getExerciseHeader(

   ): BaseResponse<List<ExerciseResponse>>

   @GET("/access/get")
   suspend fun getJobSerchHeader(

   ): BaseResponse<List<JobSearchResponse>>

}