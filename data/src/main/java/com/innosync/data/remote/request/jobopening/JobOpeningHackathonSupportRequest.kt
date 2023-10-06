package com.innosync.data.remote.request.jobopening

import com.google.gson.annotations.SerializedName

data class JobOpeningHackathonSupportRequest(
    @field:SerializedName("applicantName")
    val name: String,
    @field:SerializedName("studentId")
    val studentId: String = "1101",
    @field:SerializedName("contact")
    val contact: String,
    @field:SerializedName("introduction")
    val introduction: String,
    @field:SerializedName("portfolioLink")
    val portfolioLink: String?
)
