package com.innosync.domain.repository

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel

interface JobOpeningRepository{

    suspend fun getHackathon(): List<HackathonModel>

    suspend fun getHackathon(cnt: Int): List<HackathonModel>

    suspend fun getEat(): List<EatModel>

    suspend fun getEat(cnt: Int): List<EatModel>

    suspend fun getExercise(): List<ExerciseModel>

    suspend fun getExercise(cnt: Int): List<ExerciseModel>
}