package com.innosync.domain.usecase.mybox

import com.innosync.domain.repository.MyBoxRepository
import javax.inject.Inject

class MyBoxCompleteUseCase @Inject constructor(
    private val myBoxRepository: MyBoxRepository
) {

    suspend operator fun invoke(type : String, id : Int) = kotlin.runCatching {
        myBoxRepository.setStatusComplete(type, id)
    }
}