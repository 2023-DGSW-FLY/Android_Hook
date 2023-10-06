package com.innosync.data.remote.request

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @field:SerializedName("refreshToken")
    private val refreshToken: String
)
