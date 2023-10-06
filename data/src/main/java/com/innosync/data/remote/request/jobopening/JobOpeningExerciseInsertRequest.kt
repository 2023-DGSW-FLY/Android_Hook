package com.innosync.data.remote.request.jobopening

import com.google.gson.annotations.SerializedName

data class JobOpeningExerciseInsertRequest (
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("place")
    val place: String,
    @field:SerializedName("dateTime")
    val dateTime: String,
    @field:SerializedName("exercise")
    val exercise: String,
)