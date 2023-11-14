package com.innosync.domain.usecase

import com.innosync.domain.repository.ApplicantRepository
import javax.inject.Inject

class ApplicantUseCase @Inject constructor(
    private val applicantRepository: ApplicantRepository
) {

    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        applicantRepository.get(id = id)
    }

}