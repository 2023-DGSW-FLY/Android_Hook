package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.service.ApplicantService
import com.innosync.data.remote.utiles.hookApiCall
import com.innosync.domain.model.ApplicantModel
import com.innosync.domain.repository.ApplicantRepository
import javax.inject.Inject

class ApplicantRepositoryImpl @Inject constructor(
    private val applicantService: ApplicantService
): ApplicantRepository {
    override suspend fun get(id: Int): List<ApplicantModel> = hookApiCall{
        applicantService.get(id = id).data.toModels()
    }
}