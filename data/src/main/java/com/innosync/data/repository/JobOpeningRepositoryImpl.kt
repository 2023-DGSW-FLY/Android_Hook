package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.service.JobOpeningService
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.repository.JobOpeningRepository
import java.time.LocalDateTime
import javax.inject.Inject

class JobOpeningRepositoryImpl @Inject constructor(
    private val jobOpeningService: JobOpeningService
): JobOpeningRepository {
    override suspend fun getHackathon(): List<HackathonModel> =
        jobOpeningService.getHackathon().data.toModels()

    override suspend fun getHackathon(cnt: Int): List<HackathonModel> =
        jobOpeningService.getHackathon(cnt = cnt).data.toModels()
//        dummyData(cnt).toModels()

    override suspend fun getEat(): List<EatModel> =
        jobOpeningService.getEat().data.toModels()

    override suspend fun getEat(cnt: Int): List<EatModel> =
        jobOpeningService.getEat(cnt).data.toModels()

    override suspend fun getExercise(): List<ExerciseModel> =
        jobOpeningService.getExercise().data.toModels()

    override suspend fun getExercise(cnt: Int): List<ExerciseModel> =
        jobOpeningService.getExercise(cnt).data.toModels()
//        jobOpeningService.getHackathon(cnt = cnt).data.toModels()

    private fun dummyData(cnt: Int): MutableList<HackathonResponse> {
        var dummy = mutableListOf<HackathonResponse>()
        for (i in 1..cnt) {
            dummy.add(
                HackathonResponse(
                    id = i,
                    title = "테스트",
                    content = "내용",
                    stack = listOf("서버개발자"),
                    url = "https://rqwe",
                    status = "matching",
                    writer = "yeseon12dd31$i",
                    regDate = LocalDateTime.now(),
                    modDate = LocalDateTime.now(),
                )
            )
        }
        return dummy
    }
}