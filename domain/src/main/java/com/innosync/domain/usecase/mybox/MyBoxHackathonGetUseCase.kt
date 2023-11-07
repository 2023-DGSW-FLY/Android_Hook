package com.innosync.domain.usecase.mybox

import com.innosync.domain.repository.MyBoxRepository
import javax.inject.Inject

class MyBoxHackathonGetUseCase @Inject constructor(
    private val myBoxRepository: MyBoxRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        myBoxRepository.getHackathon()
    }


}