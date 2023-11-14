package com.innosync.hook.feature.jopoffer.model

data class JobOfferModel(
    val userName: String,
    val competitionName: String,
    val technology: String = "",
    val time: String,
    val img: String,
    val id: Int,
    val status: Boolean
)
