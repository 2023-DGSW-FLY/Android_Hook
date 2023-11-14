package com.innosync.domain.usecase.firebase

import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseInsertUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(
        userId: String,
        targetId: String
    ) = kotlin.runCatching {
        firebaseRepository.insertRoom(
            userId = userId,
            targetId = targetId
        )
    }
}