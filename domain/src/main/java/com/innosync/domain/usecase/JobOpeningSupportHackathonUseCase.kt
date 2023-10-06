package com.innosync.domain.usecase

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningSupportHackathonUseCase @Inject constructor(
    private val repository: JobOpeningRepository
) {

    suspend operator fun invoke(
        id: Int,
        name: String,
        contact: String,
        introduction: String,
        portfolioLink: String?
    ) = kotlin.runCatching {
        repository.supportHackathon(
            id = id,
            name = name,
            contact = contact,
            introduction = introduction,
            portfolioLink = portfolioLink
        )
    }
}