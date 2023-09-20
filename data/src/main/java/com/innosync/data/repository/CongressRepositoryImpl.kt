package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.response.congress.CongressResponse
import com.innosync.domain.model.CongressModel
import com.innosync.domain.repository.CongressRepository
import javax.inject.Inject

class CongressRepositoryImpl @Inject constructor(

): CongressRepository {

    override suspend fun getCongressInfo(): List<CongressModel> {
        return dummyData(14).toModels()
    }

    private fun dummyData(
        cnt: Int
    ): List<CongressResponse> {
        var data = mutableListOf<CongressResponse>()
        for (i in 0..cnt) {
            data.add(
                CongressResponse(
                    id = i,
                    title = "테스트 대회 $i",
                    dateTime = "2023월 3월 1일 ~ 2023 3월 ${i}일",
                    imgUrl = "https://pbs.twimg.com/media/ExUElF7VcAMx7jx?format=jpg&name=4096x4096"
                )
            )
        }
        return data
    }
}