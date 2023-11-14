package com.innosync.data.remote.response.jobsearch

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class JobSearchResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("stack")
    val stack: String,
    @field:SerializedName("url")
    val url: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("userId")
    val userId: String,
    @field:SerializedName("name")
    val writer: String,
    @field:SerializedName("regDate")
    val regDate: LocalDateTime,
    @field:SerializedName("modDate")
    val modDate: LocalDateTime
)
