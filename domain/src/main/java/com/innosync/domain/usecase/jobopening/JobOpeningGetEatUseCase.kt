package com.innosync.domain.usecase.jobopening

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningGetEatUseCase @Inject constructor(
    private val jobOpeningRepository: JobOpeningRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        jobOpeningRepository.getEat()
    }

    suspend operator fun invoke(cnt: Int) = kotlin.runCatching {
        jobOpeningRepository.getEat(cnt = cnt)
    }
}