package com.innosync.domain.repository

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel

interface MyBoxRepository {

    suspend fun getHackathon(): List<HackathonModel>

    suspend fun getEat(): List<EatModel>

    suspend fun getExercise(): List<ExerciseModel>

    suspend fun getJobSearch(): List<JobSearchModel>
}

