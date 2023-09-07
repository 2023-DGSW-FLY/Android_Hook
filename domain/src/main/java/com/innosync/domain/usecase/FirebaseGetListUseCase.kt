package com.innosync.domain.usecase

import com.innosync.domain.model.RoomData
import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseGetListUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(
        userId: String,
        action: (List<RoomData>) -> Unit
    ) = firebaseRepository.getRoomList(
        userId = userId,
        action = action
    )
}