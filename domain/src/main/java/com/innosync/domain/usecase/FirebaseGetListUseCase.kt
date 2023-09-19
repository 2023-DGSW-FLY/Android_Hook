package com.innosync.domain.usecase

import com.innosync.domain.model.RoomModel
import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseGetListUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(
        userId: String,
        action: (List<RoomModel>) -> Unit
    ) = firebaseRepository.getRoomList(
        userId = userId,
        action = action
    )
}