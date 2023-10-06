package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModel
import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.request.jobopening.JobOpeningEatInsertRequest
import com.innosync.data.remote.request.jobopening.JobOpeningExerciseInsertRequest
import com.innosync.data.remote.request.jobopening.JobOpeningHackathonInsertRequest
import com.innosync.data.remote.request.jobopening.JobOpeningHackathonSupportRequest
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.service.JobOpeningService
import com.innosync.data.remote.utiles.hookApiCall
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

    override suspend fun getEat(cnt: Int): List<EatModel> {
        jobOpeningService.getEat(cnt).data.let {
            println(it)
            return it.toModels()
        }
    }

    override suspend fun getExercise(): List<ExerciseModel> =
        jobOpeningService.getExercise().data.toModels()

    override suspend fun getExercise(cnt: Int): List<ExerciseModel> =
        jobOpeningService.getExercise(cnt).data.toModels()

    override suspend fun getOneEat(id: Int): EatModel =
        jobOpeningService.getOneEat(id).data.toModel()

    override suspend fun getOneExercise(id: Int): ExerciseModel =
        jobOpeningService.getOneExercise(id).data.toModel()

    override suspend fun getOneHackathon(id: Int): HackathonModel =
        jobOpeningService.getOneHackathon(id).data.toModel()


    override suspend fun insertHackathon(
        title: String,
        content: String,
        stack: List<String>,
        url: String,
    ) {
        jobOpeningService.insertHackathon(
            JobOpeningHackathonInsertRequest(
                title = title,
                content = content,
                stack = stack,
                url = url
            )
        )
    }

    override suspend fun insertEat(
        foodName: String?,
        title: String,
        content: String,
        place: String,
    ) {
        jobOpeningService.insertEat(
            JobOpeningEatInsertRequest(
                foodName = foodName,
                title = title,
                content = content,
                place = place
            )
        )
    }

    override suspend fun insertExercise(
        title: String,
        content: String,
        place: String,
        exercise: String,
        dateTime: String,
    ) {
        jobOpeningService.insertExercise(
            JobOpeningExerciseInsertRequest(
                title = title,
                content = content,
                place = place,
                dateTime = dateTime,
                exercise = exercise
            )
        )
    }

    override suspend fun supportHackathon(
        id: Int,
        name: String,
        contact: String,
        introduction: String,
        portfolioLink: String?,
    ) {
        hookApiCall {
            jobOpeningService.supportHackathon(
                id = id,
                body = JobOpeningHackathonSupportRequest(
                    name = name,
                    contact = contact,
                    introduction = introduction,
                    portfolioLink = portfolioLink
                )
            )
        }
    }
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
                    username = "q",
                    writer = "yeseon12dd31$i",
                    regDate = LocalDateTime.now(),
                    modDate = LocalDateTime.now(),
                )
            )
        }
        return dummy
    }
}