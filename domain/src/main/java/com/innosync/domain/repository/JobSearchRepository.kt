package com.innosync.domain.repository

import com.innosync.domain.model.JobSearchModel

interface JobSearchRepository {

    suspend fun get(): List<JobSearchModel>

    suspend fun get(cnt: Int): List<JobSearchModel>
}