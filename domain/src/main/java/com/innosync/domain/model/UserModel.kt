package com.innosync.domain.model

data class UserModel(
    val id: Int,
    val userRole: String?,
    val userAccount: String?,
    val userName: String?,
    val userInfo: String?,
    val email: String?,
    val githubURL: String?,
    val portfolioURL: String?,
)