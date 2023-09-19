package com.innosync.domain.repository

import com.innosync.domain.model.CongressModel

interface CongressRepository {

    suspend fun getCongressInfo(): List<CongressModel>
}