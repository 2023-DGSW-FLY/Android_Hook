package com.innosync.domain.usecase.firebase

import com.innosync.domain.repository.FirebaseTokenRepository
import javax.inject.Inject

class FirebaseTokenInsertUseCase @Inject constructor(
    private val firebaseTokenRepository: FirebaseTokenRepository
) {

    suspend operator fun invoke(token: String) = kotlin.runCatching {
        firebaseTokenRepository.insert(token)
    }
}