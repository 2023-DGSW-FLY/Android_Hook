package com.innosync.data.remote.response

import com.google.gson.annotations.SerializedName

data class JoinResponse (
    @field:SerializedName("resultCode")
    val success: String
)