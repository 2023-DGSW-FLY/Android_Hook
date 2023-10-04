package com.innosync.data.remote.response.token

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @field:SerializedName("accessToken")
    val accessToken: String
)