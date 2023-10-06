package com.innosync.domain.usecase.auth

import com.innosync.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        userAccount: String,
        password: String
    ) = kotlin.runCatching {
        authRepository.login(
            userAccount = userAccount,
            password = password
        )
    }
}