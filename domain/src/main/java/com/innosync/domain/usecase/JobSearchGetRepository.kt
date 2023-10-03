package com.innosync.domain.usecase

import com.innosync.domain.repository.JobSearchRepository
import javax.inject.Inject

class JobSearchGetRepository @Inject constructor(
    private val jobSearchRepository: JobSearchRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        jobSearchRepository.get()
    }

    suspend operator fun invoke(cnt: Int) = kotlin.runCatching {
        jobSearchRepository.get(cnt)
    }
}