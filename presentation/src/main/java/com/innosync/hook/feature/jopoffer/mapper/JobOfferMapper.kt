package com.innosync.hook.feature.jopoffer.mapper

import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.hook.R
import com.innosync.hook.feature.jopoffer.model.JobOfferModel
import com.innosync.hook.util.Utils
import com.innosync.hook.util.toStringDate


internal fun HackathonModel.toJobOfferModel() =
    JobOfferModel(
        userName = writer,
        competitionName = title,
        technology = stack.toTechnology(),
        time = regDate.second.toLong().toStringDate(),
        img = "https://i.discogs.com/YCopd9B5j4KEu0_mA-L8GirzXpRoHKAFJjDEkntsRTM/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9BLTIyMjYz/MDYtMTU5NzMzMjM5/Mi03MzMwLmpwZWc.jpeg",
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
        technology = "",
        time = regDate.second.toLong().toStringDate(),
        img = "https://i.discogs.com/YCopd9B5j4KEu0_mA-L8GirzXpRoHKAFJjDEkntsRTM/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9BLTIyMjYz/MDYtMTU5NzMzMjM5/Mi03MzMwLmpwZWc.jpeg",
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
        technology = "",
        time = regDate.second.toLong().toStringDate(),
        img = "https://i.discogs.com/YCopd9B5j4KEu0_mA-L8GirzXpRoHKAFJjDEkntsRTM/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9BLTIyMjYz/MDYtMTU5NzMzMjM5/Mi03MzMwLmpwZWc.jpeg",
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