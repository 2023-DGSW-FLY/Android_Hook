package com.innosync.data.remote.service

import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.jobopening.EatResponse
import com.innosync.data.remote.response.jobopening.ExerciseResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.util.Env
import com.innosync.domain.model.EatModel
import retrofit2.http.GET
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
}