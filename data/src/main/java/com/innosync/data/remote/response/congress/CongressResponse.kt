package com.innosync.data.remote.response.congress

import com.google.gson.annotations.SerializedName

data class CongressResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("imgUrl")
    val imgUrl: String,
    @field:SerializedName("dateTime")
    val dateTime: String,
    @field:SerializedName("url")
    val url: String,
)
