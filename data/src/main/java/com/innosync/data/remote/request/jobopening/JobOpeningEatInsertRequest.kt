package com.innosync.data.remote.request.jobopening

import com.google.gson.annotations.SerializedName

data class JobOpeningEatInsertRequest (
    @field:SerializedName("foodName")
    val foodName: String?,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("place")
    val place: String,
)