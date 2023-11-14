package com.innosync.data.remote.request

import com.google.gson.annotations.SerializedName

data class UserGetTheseRequest (
    @SerializedName("userId")
    val users: List<String>,
)