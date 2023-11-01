package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.JoinResponse
import com.innosync.data.remote.response.jobopening.EatResponse
import com.innosync.data.remote.response.jobopening.ExerciseResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.domain.model.JobSearchModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

   @POST("/{type}/status/complete/{id}")
   suspend fun  setStatusCompleteApi(@Path ("type") type: String, @Path("id") id : Int) : JoinResponse
   @POST("/{type}/status/match/{id}")
   suspend fun  setStatusMatchingApi(@Path ("type") type: String, @Path("id") id : Int) : JoinResponse




}