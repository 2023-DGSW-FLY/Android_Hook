package com.innosync.hook.feature.home

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel

internal fun HackathonModel.toHomeRvModel() =
    HomeRvData(
        status = status,
        writer = writer,
        title = title
    )

internal fun List<HackathonModel>.toHomeRvModels() =
    this.map {
        it.toHomeRvModel()
    }

@JvmName("jobSearchModelToHomeRvModel")
internal fun JobSearchModel.toHomeRvModel() =
    HomeRvData(
        status = status,
        writer =  writer,
        title = content.substring(0, 9),
        type = 0
    )
@JvmName("jobSearchModelToHomeRvModels")
internal fun List<JobSearchModel>.toHomeRvModels() =
    this.map {
        it.toHomeRvModel()
    }

@JvmName("EatModelToHomeRvModel")
internal fun EatModel.toHomeRvModel() =
    HomeRvData(
        status = status,
        writer =  writer,
        title = title,
        type = 0
    )
@JvmName("EatModelToHomeRvModels")
internal fun List<EatModel>.toHomeRvModels() =
    this.map {
        it.toHomeRvModel()
    }

@JvmName("ExerciseModelToHomeRvModel")
internal fun ExerciseModel.toHomeRvModel() =
    HomeRvData(
        status = status,
        writer = username,
        title = title,
        type = 0
    )
@JvmName("ExerciseModelToHomeRvModels")
internal fun List<ExerciseModel>.toHomeRvModels() =
    this.map {
        it.toHomeRvModel()
    }