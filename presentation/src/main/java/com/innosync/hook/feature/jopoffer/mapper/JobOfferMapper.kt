package com.innosync.hook.feature.jopoffer.mapper

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.hook.R
import com.innosync.hook.feature.jopoffer.model.JobOfferModel
import com.innosync.hook.util.Utils
import com.innosync.hook.util.toImageUrl
import com.innosync.hook.util.toStringDate


internal fun HackathonModel.toJobOfferModel() =
    JobOfferModel(
        userName = writer,
        competitionName = title,
        technology = stack.toTechnology(),
        time = modDate.toStringDate(),
        img = userId.toString().toImageUrl(),
        id = id
    )

@JvmName("HackathonModelToJobOfferModels")
internal fun List<HackathonModel>.toJobOfferModels() =
    this.map {
        it.toJobOfferModel()
    }

internal fun EatModel.toJobOfferModel() =
    JobOfferModel(
        userName = writer,
        competitionName = title,
        time = modDate.toStringDate(),
        img = userId.toImageUrl(),
        id = id
    )
@JvmName("EatModelToJobOfferModels")
internal fun List<EatModel>.tooJobOfferModels() =
    this.map {
        it.toJobOfferModel()
    }

internal fun ExerciseModel.tooJobOfferModel() =
    JobOfferModel(
        userName = username,
        competitionName = exercise,
        time = modDate.toStringDate(),
        img = userId.toImageUrl(),
        id = id
    )
@JvmName("ExerciseModelToJobOfferModels")
internal fun List<ExerciseModel>.toJobOfferModels() =
    this.map {
        it.tooJobOfferModel()
    }



internal fun List<String>.toTechnology(): String {
    var result  = ""
    for (i in this) {
        result += "${i}\n"
    }
    return result

}