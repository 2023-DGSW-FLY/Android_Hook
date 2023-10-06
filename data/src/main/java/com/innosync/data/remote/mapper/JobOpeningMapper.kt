package com.innosync.data.remote.mapper

import com.innosync.data.remote.response.jobopening.EatResponse
import com.innosync.data.remote.response.jobopening.ExerciseResponse
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel

internal fun HackathonResponse.toModel() =
    HackathonModel(
        id = id,
        title = title,
        content = content,
        stack = stack,
        url = url,
        status = status,
        username = username,
        writer = writer,
        regDate = regDate,
        modDate = modDate
    )
internal fun List<HackathonResponse>.toModels() =
    this.map {
        it.toModel()
    }

@JvmName("EatResponseToModel")
internal fun EatResponse.toModel() =
    EatModel(
        id = id,
        title = title,
        content = content,
        place = place,
        dateTime = dateTime,
        status = status,
        username = username,
        writer = writer,
        userId = userId,
        regDate = regDate,
        modDate = modDate
    )

@JvmName("EatResponseToModels")
internal fun List<EatResponse>.toModels() =
    this.map {
        it.toModel()
    }
@JvmName("ExerciseResponseToModel")
internal fun ExerciseResponse.toModel() =
    ExerciseModel(
        id = id,
        title = title,
        content = content,
        place = place,
        dateTime = dateTime,
        username = username,
        userId = userId,
        writer = writer,
        status = status,
        exercise = exercise,
        regDate = regDate,
        modDate = modDate
    )
@JvmName("ExerciseResponseToModels")
internal fun List<ExerciseResponse>.toModels() =
    this.map {
        it.toModel()
    }