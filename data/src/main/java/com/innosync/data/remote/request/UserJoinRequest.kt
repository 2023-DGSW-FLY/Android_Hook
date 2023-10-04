package com.innosync.data.remote.request

import com.google.gson.annotations.SerializedName

data class UserJoinRequest(
    @SerializedName("userAccount")
    val userAccount: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("userInfo")
    val userInfo: String,
    @SerializedName("githubURL")
    val githubURL: String,
    @SerializedName("portfolioURL")
    val portfolioURL: String
)

