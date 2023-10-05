package com.innosync.domain.repository

import com.innosync.domain.model.UserModel
import javax.inject.Inject

interface UserRepository {

    suspend fun get(): UserModel
}