package com.innosync.data.remote.service

import com.innosync.data.remote.response.Alarm.AlarmResponse
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.congress.CongressResponse
import com.innosync.data.util.Env
import retrofit2.http.GET

interface AlarmService {

    @GET(Env.Alarm.get)
    suspend fun get(

    ): BaseResponse<List<AlarmResponse>>
}