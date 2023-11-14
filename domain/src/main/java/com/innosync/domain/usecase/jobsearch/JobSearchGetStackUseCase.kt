package com.innosync.domain.usecase.jobsearch

import com.innosync.domain.repository.JobSearchRepository
import javax.inject.Inject

class JobSearchGetStackUseCase @Inject constructor(
    private val repository: JobSearchRepository
) {
    suspend operator fun invoke(category: String) = kotlin.runCatching {
        repository.getStack(category)
    }

}