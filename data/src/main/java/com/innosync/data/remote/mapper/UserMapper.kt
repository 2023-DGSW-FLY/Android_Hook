package com.innosync.data.remote.mapper

import com.innosync.data.remote.response.user.UserResponse
import com.innosync.domain.model.UserModel

internal fun UserResponse.toModel() =
    UserModel(
        id = id,
        userRole = userRole,
        userAccount = userAccount,
        userName = userName,
        userInfo = userInfo,
        email = email,
        githubURL = githubURL,
        portfolioURL = portfolioURL
    )