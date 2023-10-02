package com.innosync.data.remote.response.jobopening

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class HackathonResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("stack")
    val stack: List<String>,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("writer")
    val writer: String,
    @field:SerializedName("regDate")
    val regDate: LocalDateTime,
    @field:SerializedName("modDate")
    val modDate: LocalDateTime
)
