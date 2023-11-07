package com.innosync.data.remote.response.mybox

import com.google.gson.annotations.SerializedName

data class ApplicantResponse(
    @field:SerializedName("applicantName")
    val applicantName: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("studentId")
    val studentId: String,

    @field:SerializedName("contact")
    val contact: String,

    @field:SerializedName("introduction")
    val introduction: String,

    @field:SerializedName("portfolioLink")
    val portfolioLink: String?,

    @field:SerializedName("userId")
    val userId: String
)