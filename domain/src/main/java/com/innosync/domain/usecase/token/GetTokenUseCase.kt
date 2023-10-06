package com.innosync.domain.usecase.token

import com.innosync.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        tokenRepository.getToken()
    }
}