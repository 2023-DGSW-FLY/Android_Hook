package com.innosync.data.repository

import android.graphics.Paint.Join
import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.response.JoinResponse
import com.innosync.data.remote.service.MyBoxService
import com.innosync.data.remote.utiles.hookApiCall
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel
import com.innosync.domain.repository.MyBoxRepository
import javax.inject.Inject

class MyBoxRepositoryImpl @Inject constructor(
    private val myBoxService: MyBoxService
): MyBoxRepository {
    override suspend fun getHackathon(): List<HackathonModel> = hookApiCall {
        myBoxService.getHackertonHeader().data.toModels()
    }

    override suspend fun getEat(): List<EatModel> = hookApiCall {
        myBoxService.getMealHeader().data.toModels()
    }

    override suspend fun getExercise(): List<ExerciseModel> = hookApiCall {
        myBoxService.getExerciseHeader().data.toModels()
    }

    override suspend fun getJobSearch(): List<JobSearchModel> = hookApiCall {
        myBoxService.getJobSerchHeader().data.toModels()
    }
//구현부 : 상태를 완료, 취소 하는 곳.
    override suspend fun setStatusComplete(type : String, id : Int): Unit = hookApiCall {
        myBoxService.setStatusCompleteApi(type, id)
    }

    override suspend fun setStatusMatching(type: String, id: Int): Unit = hookApiCall {
        myBoxService.setStatusMatchingApi(type, id)
    }


}