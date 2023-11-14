package com.innosync.domain.usecase.jobopening

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningInsertHackathonUseCase @Inject constructor(
    private val jobOpeningRepository: JobOpeningRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        stack: List<String>,
        url: String
    ) = kotlin.runCatching {
        jobOpeningRepository.insertHackathon(
            title = title,
            content = content,
            stack = stack,
            url = url
        )
    }
}