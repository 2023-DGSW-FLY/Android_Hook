package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModel
import com.innosync.data.remote.request.UserGetTheseRequest
import com.innosync.data.remote.service.UserService
import com.innosync.data.remote.utiles.hookApiCall
import com.innosync.domain.model.UserModel
import com.innosync.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
): UserRepository {
    override suspend fun get(): UserModel = hookApiCall {
        userService.getMyInfo().data.toModel()
    }

}