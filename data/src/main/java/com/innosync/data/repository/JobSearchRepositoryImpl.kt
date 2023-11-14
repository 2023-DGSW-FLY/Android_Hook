package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModel
import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.request.jobsearch.JobSearchInsertRequest
import com.innosync.data.remote.response.jobsearch.JobSearchResponse
import com.innosync.data.remote.service.JobSearchService
import com.innosync.data.remote.utiles.hookApiCall
import com.innosync.domain.model.JobSearchModel
import com.innosync.domain.repository.JobSearchRepository
import java.time.LocalDateTime
import javax.inject.Inject

class JobSearchRepositoryImpl @Inject constructor(
    private val jobSearchService: JobSearchService
): JobSearchRepository {
    override suspend fun get(): List<JobSearchModel> = hookApiCall {
        jobSearchService.get().data.toModels()
    }

    override suspend fun get(cnt: Int): List<JobSearchModel> = hookApiCall {
        jobSearchService.get(cnt).data.toModels()
    }
    override suspend fun getStack(category: String): List<JobSearchModel> = hookApiCall {
        jobSearchService.getStack(category).data.toModels()
    }

    override suspend fun insert(stack: String, content: String, url: String): Unit = hookApiCall {
        jobSearchService.insert(
            JobSearchInsertRequest(
                stack = stack,
                content = content,
                url = url
            )
        )
    }

    override suspend fun getThat(id: Int): JobSearchModel = hookApiCall {
        jobSearchService.getThat(
            id = id
        ).data.toModel()
    }
//        dummyData(cnt).toModels()


//    private fun dummyData(cnt: Int): MutableList<JobSearchResponse> {
//        var dummy = mutableListOf<JobSearchResponse>()
//        for (i in 1..cnt) {
//            dummy.add(
//                JobSearchResponse(
//                    id = i,
//                    content = "내용",
//                    stack = "서버개발자",
//                    url = "https://rqwe",
//                    status = "matching",
//                    writer = "yeseon12dd31$i",
//                    regDate = LocalDateTime.now(),
//                    modDate = LocalDateTime.now(),
//                )
//            )
//        }
//        return dummy
//    }

}