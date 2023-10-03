package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.response.jobopening.HackathonResponse
import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.data.remote.service.JobSearchService
import com.innosync.domain.model.JobSearchModel
import com.innosync.domain.repository.JobSearchRepository
import java.time.LocalDateTime
import javax.inject.Inject

class JobSearchRepositoryImpl @Inject constructor(
    private val jobSearchService: JobSearchService
): JobSearchRepository {
    override suspend fun get(): List<JobSearchModel> =
        jobSearchService.get().data.toModels()

    override suspend fun get(cnt: Int): List<JobSearchModel> =
        jobSearchService.get(cnt).data.toModels()
//        dummyData(cnt).toModels()


    private fun dummyData(cnt: Int): MutableList<JobSearchResponse> {
        var dummy = mutableListOf<JobSearchResponse>()
        for (i in 1..cnt) {
            dummy.add(
                JobSearchResponse(
                    id = i,
                    title = "테스트",
                    content = "내용",
                    stack = listOf("서버개발자"),
                    url = "https://rqwe",
                    status = "matching",
                    writer = "yeseon12dd31$i",
                    regDate = LocalDateTime.now(),
                    modDate = LocalDateTime.now(),
                )
            )
        }
        return dummy
    }

}