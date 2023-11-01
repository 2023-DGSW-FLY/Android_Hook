package com.innosync.domain.repository

import com.innosync.domain.model.ApplicantModel

interface ApplicantRepository {
    suspend fun get(id: Int): List<ApplicantModel>

}