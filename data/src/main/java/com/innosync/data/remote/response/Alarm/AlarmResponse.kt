package com.innosync.data.remote.response.Alarm

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AlarmResponse(
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("regDate")
    val regDate: LocalDateTime
)