package com.innosync.data.remote.mapper

import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.domain.model.JobSearchModel

internal fun JobSearchResponse.toModel() =
    JobSearchModel(
        id = id,
        content = content,
        stack = stack,
        url = url,
        status = status,
        writer = writer,
        regDate = regDate,
        modDate = modDate,
        userId = userId
    )

internal fun List<JobSearchResponse>.toModels() =
    this.map {
        it.toModel()
    }