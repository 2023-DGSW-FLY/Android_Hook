package com.innosync.data.remote.mapper

import com.innosync.data.remote.response.Alarm.AlarmResponse
import com.innosync.domain.model.AlarmModel


internal fun AlarmResponse.toModel() =
    AlarmModel(
        content = content,
        regDate = regDate,
        type = type
    )

internal fun List<AlarmResponse>.toModels() =
    this.map {
        it.toModel()
    }