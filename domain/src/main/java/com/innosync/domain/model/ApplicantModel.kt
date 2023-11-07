package com.innosync.domain.model

data class ApplicantModel(
    val id: Int,
    val applicantName: String,
    val studentId: String,
    val contact: String,
    val introduction: String,
    val portfolioLink: String?,
    val userId: String
)
