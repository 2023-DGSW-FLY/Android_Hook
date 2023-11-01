package com.innosync.data.remote.mapper

import com.innosync.data.remote.response.mybox.ApplicantResponse
import com.innosync.domain.model.ApplicantModel

internal fun ApplicantResponse.toModel() =
    ApplicantModel(
        id = id,
        applicantName = applicantName
    )

internal fun List<ApplicantResponse>.toModels() =
    this.map {
        it.toModel()
    }