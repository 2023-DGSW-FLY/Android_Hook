package com.innosync.domain.usecase.user

import com.innosync.domain.repository.UserRepository
import javax.inject.Inject

class UserGetInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        userRepository.get()
    }
}