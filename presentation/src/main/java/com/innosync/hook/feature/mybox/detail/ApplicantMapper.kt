package com.innosync.hook.feature.mybox.detail

import com.innosync.domain.model.ApplicantModel

internal fun ApplicantModel.toRvMyBoxModel() =
    ApplicantsRvModel(
        name = applicantName,
        id = id
    )

internal fun List<ApplicantModel>.toRvMyBoxModels() =
    this.map {
        it.toRvMyBoxModel()
    }