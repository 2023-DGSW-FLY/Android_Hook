package com.innosync.domain.usecase.jobsearch

import com.innosync.domain.repository.JobSearchRepository
import javax.inject.Inject

class JobSearchGetThatUseCase @Inject constructor(
    private val repository: JobSearchRepository
) {
    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        repository.getThat(
            id = id
        )
    }

}