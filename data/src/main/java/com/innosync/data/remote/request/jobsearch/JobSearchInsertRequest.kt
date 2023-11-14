package com.innosync.data.remote.request.jobsearch

import com.google.gson.annotations.SerializedName

data class JobSearchInsertRequest (
    @field:SerializedName("stack")
    val stack: String,
    @field:SerializedName("content")
    val content: String,
    @field:SerializedName("url")
    val url: String
)