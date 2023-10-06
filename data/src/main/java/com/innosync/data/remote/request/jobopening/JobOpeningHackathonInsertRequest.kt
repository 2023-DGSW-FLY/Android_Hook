package com.innosync.data.remote.request.jobopening

import com.google.gson.annotations.SerializedName

data class JobOpeningHackathonInsertRequest (
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("stack")
    val stack: List<String>,
    @field:SerializedName("url")
    val url: String,
)