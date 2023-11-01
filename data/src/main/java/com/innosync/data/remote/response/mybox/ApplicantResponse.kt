package com.innosync.data.remote.response.mybox

import com.google.gson.annotations.SerializedName

data class ApplicantResponse(
    @field:SerializedName("applicantName")
    val applicantName: String,

    @field:SerializedName("id")
    val id: Int
)