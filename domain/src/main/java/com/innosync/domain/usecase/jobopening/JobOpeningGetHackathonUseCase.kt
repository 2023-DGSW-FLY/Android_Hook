package com.innosync.domain.usecase.jobopening

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningGetHackathonUseCase @Inject constructor(
    private val jobOpeningRepository: JobOpeningRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        jobOpeningRepository.getHackathon()
    }

    suspend operator fun invoke(cnt: Int) = kotlin.runCatching {
        jobOpeningRepository.getHackathon(cnt = cnt)
    }
}