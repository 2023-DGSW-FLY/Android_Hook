package com.innosync.hook.feature.jopsearch

import com.innosync.domain.model.JobSearchModel
import com.innosync.hook.util.toSlice
import com.innosync.hook.util.toStringDate

internal fun JobSearchModel.toRvModel() =
    JobSearchRvModel(
        userName = writer,
        time = modDate.toStringDate(),
        detail = content.toSlice(8)
    )

internal fun List<JobSearchModel>.toRvModels() =
    this.map {
        it.toRvModel()
    }