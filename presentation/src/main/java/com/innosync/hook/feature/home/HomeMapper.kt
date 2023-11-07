package com.innosync.hook.feature.home

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel
import com.innosync.hook.util.toSlice
import com.innosync.hook.util.toStringDate

internal fun HackathonModel.toHomeRvModel() =
    HomeRvData(
        status = status,
        writer = writer,
        title = title.toSlice(9),
        time = modDate.toStringDate(),
        type = 0
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
        title = content.toSlice(9),
        type = 1,
        time = modDate.toStringDate()
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
        title = title.toSlice(9),
        type = 0,
        time = modDate.toStringDate()
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
        title = title.toSlice(9),
        type = 0,
        time = modDate.toStringDate()
    )
@JvmName("ExerciseModelToHomeRvModels")
internal fun List<ExerciseModel>.toHomeRvModels() =
    this.map {
        it.toHomeRvModel()
    }