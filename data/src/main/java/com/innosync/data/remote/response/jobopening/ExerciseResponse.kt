package com.innosync.data.remote.response.jobopening

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ExerciseResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("place")
    val place: String,
    @field:SerializedName("dateTime")
    val dateTime: String,
    @field:SerializedName("userName")
    val username: String,
    @field:SerializedName("writer")
    val writer: String,
    @field:SerializedName("userId")
    val userId: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("exercise")
    val exercise: String,
    @field:SerializedName("regDate")
    val regDate: LocalDateTime,
    @field:SerializedName("modDate")
    val modDate: LocalDateTime,
)