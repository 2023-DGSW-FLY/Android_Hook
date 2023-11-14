package com.innosync.data.remote.request

import com.google.gson.annotations.SerializedName

data class UserLoginRequest (
    @SerializedName("userAccount")
    val userAccount: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("fireBaseToken")
    val fireBaseToken: String,
)