package com.innosync.hook.feature.jopsearch

import com.innosync.domain.model.JobSearchModel
import com.innosync.hook.util.toStringDate

internal fun JobSearchModel.toRvModel() =
    JobSearchRvModel(
        userName = writer,
        time = regDate.second.toLong().toStringDate(),
        detail = content.substring(0, if(content.length > 8) 8 else content.length)
    )

internal fun List<JobSearchModel>.toRvModels() =
    this.map {
        it.toRvModel()
    }