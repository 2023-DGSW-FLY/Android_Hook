package com.innosync.hook.feature.mybox

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel

internal fun HackathonModel.toMyBoxRvData() =
    MyBoxRvData(
        content = content,
        status = status,
        date = regDate,
        id = id,
        type = "hackathon"
    )

internal fun List<HackathonModel>.toMyBoxRvDatasHackathon() =
    this.map {
        it.toMyBoxRvData()
    }

internal fun EatModel.toMyBoxRvData() =
    MyBoxRvData(
        content = content,
        status = status,
        date = regDate,
        id = id,
        type = "food"
    )

internal fun List<EatModel>.toMyBoxRvDatasEat() =
    this.map {
        it.toMyBoxRvData()
    }

internal fun ExerciseModel.toMyBoxRvData() =
    MyBoxRvData(
        content = content,
        status = status,
        date = regDate,
        id = id,
        type = "exercise"
    )

internal fun List<ExerciseModel>.toMyBoxRvDatasExercise() =
    this.map {
        it.toMyBoxRvData()
    }

internal fun JobSearchModel.toMyBoxRvData() =
    MyBoxRvData(
        content = content,
        status = status,
        date = regDate,
        id = id,
        type = "access"
    )

internal fun List<JobSearchModel>.toMyBoxRvDatasJobSearch() =
    this.map {
        it.toMyBoxRvData()
    }
