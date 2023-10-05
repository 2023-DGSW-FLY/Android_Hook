package com.innosync.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("userRole")
    val userRole: String?,
    @field:SerializedName("userAccount")
    val userAccount: String?,
    @field:SerializedName("user_name")
    val userName: String?,
    @field:SerializedName("user_info")
    val userInfo: String?,
    @field:SerializedName("email")
    val email: String?,
    @field:SerializedName("github_url")
    val githubURL: String?,
    @field:SerializedName("portfolio_url")
    val portfolioURL: String?,
)