package com.innosync.data.remote.service

import com.innosync.data.remote.request.jobopening.JobOpeningEatInsertRequest
import com.innosync.data.remote.request.jobopening.JobOpeningExerciseInsertRequest
import com.innosync.data.remote.request.jobopening.JobOpeningHackathonInsertRequest
import com.innosync.data.remote.request.jobopening.JobOpeningHackathonSupportRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.TestResponse
import com.innosync.data.remote.response.jobopening.EatResponse
import com.innosync.data.remote.response.jobopening.ExerciseResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.util.Env
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface JobOpeningService {

    @GET(Env.JobOpening.hackathonGet)
    suspend fun getHackathon(

    ): BaseResponse<List<HackathonResponse>>

    @GET(Env.JobOpening.hackathonGet)
    suspend fun getHackathon(
        @Query("cnt") cnt: Int
    ): BaseResponse<List<HackathonResponse>>

    @GET(Env.JobOpening.eatGet)
    suspend fun getEat(

    ):  BaseResponse<List<EatResponse>>

    @GET(Env.JobOpening.eatGet)
    suspend fun getEat(
        @Query("cnt") cnt: Int
    ):  BaseResponse<List<EatResponse>>

    @GET(Env.JobOpening.exerciseGet)
    suspend fun getExercise(

    ):  BaseResponse<List<ExerciseResponse>>

    @GET(Env.JobOpening.exerciseGet)
    suspend fun getExercise(
        @Query("cnt") cnt: Int
    ):  BaseResponse<List<ExerciseResponse>>

    @POST(Env.JobOpening.hackathonInsert)
    suspend fun insertHackathon(
        @Body body: JobOpeningHackathonInsertRequest
    ): TestResponse

    @POST(Env.JobOpening.eatInsert)
    suspend fun insertEat(
        @Body body: JobOpeningEatInsertRequest
    ): TestResponse

    @POST(Env.JobOpening.exerciseInsert)
    suspend fun insertExercise(
        @Body body: JobOpeningExerciseInsertRequest
    ): TestResponse


    @GET("${Env.JobOpening.eatGetOne}/{id}")
    suspend fun getOneEat(
        @Path("id") id: Int
    ): BaseResponse<EatResponse>

    @GET("${Env.JobOpening.exerciseGetOne}/{id}")
    suspend fun getOneExercise(
        @Path("id") id: Int
    ): BaseResponse<ExerciseResponse>

    @GET("${Env.JobOpening.hackathonGetOne}/{id}")
    suspend fun getOneHackathon(
        @Path("id") id: Int
    ): BaseResponse<HackathonResponse>

    @POST("${Env.JobOpening.hackathonSupport}/{id}/apply")
    suspend fun supportHackathon(
        @Path("id") id: Int,
        @Body body: JobOpeningHackathonSupportRequest
    ): TestResponse


}