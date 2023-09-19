package com.innosync.domain.usecase

import com.innosync.domain.repository.CongressRepository
import javax.inject.Inject

class CongressUseCase @Inject constructor(
    private val repository: CongressRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        repository.getCongressInfo()
    }
}