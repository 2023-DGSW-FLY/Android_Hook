package com.innosync.data.remote.request.profilefix

import com.google.gson.annotations.SerializedName

data class ProfileFixRequest(
    @SerializedName("userAccount")
    val userAccount: String,
    @SerializedName("user_name")
    val user_name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("user_info")
    val user_info: String,
    @SerializedName("github_url")
    val github_url: String,
    @SerializedName("portfolio_url")
    val portfolio_url: String
)
