package com.innosync.domain.usecase

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningGetOneHackathonUseCase @Inject constructor(
    private val repository: JobOpeningRepository
) {

    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        repository.getOneHackathon(id)
    }
}