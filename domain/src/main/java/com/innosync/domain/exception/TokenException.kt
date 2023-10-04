package com.innosync.domain.exception

private const val EXPIRED_REFRESH_TOKEN_MESSAGE = "token_exception"

class ExpiredRefreshTokenException : RuntimeException() {
    override val message: String
        get() = EXPIRED_REFRESH_TOKEN_MESSAGE
}