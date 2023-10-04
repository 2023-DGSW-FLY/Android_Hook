package com.innosync.data.repository

import com.innosync.data.remote.request.UserLoginRequest
import com.innosync.data.remote.service.LoginService
import com.innosync.domain.repository.LoginRepository
import javax.inject.Inject
import kotlin.math.log

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): LoginRepository {

    override suspend fun login(userAccount: String, password: String) {
        loginService.login(
            UserLoginRequest(
                userAccount, password
            )
        )
    }
}