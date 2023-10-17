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
    fun getHackertonHeader(

    ): BaseResponse<List<HackathonResponse>>

    @GET("/hackathon/all")
    fun getEatHeader(

    ): BaseResponse<List<EatResponse>>

   @GET("/hackathon/all")
   fun getExerciseHeader(

   ): BaseResponse<List<ExerciseResponse>>

   @GET("/hackathon/all")
   fun getJobSerchHeader(

   ): BaseResponse<List<JobSearchResponse>>

}