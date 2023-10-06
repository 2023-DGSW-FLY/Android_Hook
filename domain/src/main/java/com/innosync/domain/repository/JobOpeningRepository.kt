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

    suspend fun getOneEat(id: Int): EatModel

    suspend fun getOneExercise(id: Int): ExerciseModel

    suspend fun getOneHackathon(id: Int): HackathonModel

    suspend fun insertHackathon(title: String, content: String, stack: List<String>, url: String)

    suspend fun insertEat(foodName: String?, title: String, content: String, place: String)

    suspend fun insertExercise(title: String, content: String, place: String, exercise: String, dateTime: String)

    suspend fun supportHackathon(id: Int, name: String, contact: String, introduction: String, portfolioLink: String?)
}