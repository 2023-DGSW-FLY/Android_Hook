package com.innosync.data.remote.mapper

import com.innosync.data.remote.response.congress.CongressResponse
import com.innosync.domain.model.CongressModel


internal fun CongressResponse.toModel() =
    CongressModel(
        id = id,
        title = title,
        imgUrl = imgUrl,
        dateTime = dateTime
    )

internal fun List<CongressResponse>.toModels() =
    this.map { it.toModel() }