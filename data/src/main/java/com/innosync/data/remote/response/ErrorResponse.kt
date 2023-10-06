package com.innosync.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,
)