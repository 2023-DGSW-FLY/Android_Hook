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
    @SerializedName("user_info")
    val userInfo: String,
    @SerializedName("github_url")
    val githubURL: String,
    @SerializedName("portfolio_url")
    val portfolioURL: String
)

