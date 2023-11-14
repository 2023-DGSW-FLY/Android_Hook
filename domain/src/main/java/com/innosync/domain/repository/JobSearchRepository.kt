package com.innosync.domain.repository

import com.innosync.domain.model.JobSearchModel

interface JobSearchRepository {

    suspend fun get(): List<JobSearchModel>

    suspend fun get(cnt: Int): List<JobSearchModel>

    suspend fun getStack(category: String): List<JobSearchModel>

    suspend fun insert(stack: String, content: String, url: String)

    suspend fun getThat(id: Int): JobSearchModel

}