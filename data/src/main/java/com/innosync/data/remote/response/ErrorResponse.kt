package com.innosync.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @field:SerializedName("Fail")
    val Fail: String,
)