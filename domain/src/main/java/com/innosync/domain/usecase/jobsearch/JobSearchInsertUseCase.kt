package com.innosync.domain.usecase.jobsearch

import com.innosync.domain.repository.JobSearchRepository
import javax.inject.Inject

class JobSearchInsertUseCase @Inject constructor(
    private val repository: JobSearchRepository
) {
    suspend operator fun invoke(stack: String, content: String, url: String) = kotlin.runCatching {
        repository.insert(
            stack = stack,
            content = content,
            url = url
        )
    }

}