package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.service.MyBoxService
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel
import com.innosync.domain.repository.MyBoxRepository
import javax.inject.Inject

class MyBoxRepositoryImpl @Inject constructor(
    private val myBoxService: MyBoxService
): MyBoxRepository {
    override suspend fun getHackathon(): List<HackathonModel> =
        myBoxService.getHackertonHeader().data.toModels()

    override suspend fun getEat(): List<EatModel> =
        myBoxService.getEatHeader().data.toModels()

    override suspend fun getExercise(): List<ExerciseModel> =
        myBoxService.getExerciseHeader().data.toModels()


    override suspend fun getMyWrite(): List<JobSearchModel> =
        myBoxService.getJobSerchHeader().data.toModels()
}