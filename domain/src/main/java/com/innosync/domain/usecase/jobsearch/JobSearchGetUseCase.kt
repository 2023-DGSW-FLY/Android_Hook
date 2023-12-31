package com.innosync.domain.usecase.jobsearch

import com.innosync.domain.repository.JobSearchRepository
import javax.inject.Inject

class JobSearchGetUseCase @Inject constructor(
    private val jobSearchRepository: JobSearchRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        jobSearchRepository.get()
    }

    suspend operator fun invoke(cnt: Int) = kotlin.runCatching {
        jobSearchRepository.get(cnt)
    }
}