package com.innosync.data.local.mapper

import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.domain.model.Token

internal fun TokenEntity.toModel(): Token {
    return Token(
        token = token,
        refreshToken = refreshToken
    )
}

internal fun Token.toEntity(): TokenEntity {
    return TokenEntity(
        token = token,
        refreshToken = refreshToken
    )
}