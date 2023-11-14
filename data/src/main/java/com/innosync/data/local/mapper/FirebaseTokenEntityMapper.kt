package com.innosync.data.local.mapper

import com.innosync.data.local.entity.token.FirebaseTokenEntity
import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.domain.model.FirebaseTokenModel
import com.innosync.domain.model.Token

internal fun FirebaseTokenEntity.toModel(): FirebaseTokenModel {
    return FirebaseTokenModel(
        token = token
    )
}

internal fun FirebaseTokenModel.toEntity(): FirebaseTokenEntity {
    return FirebaseTokenEntity(
        token = token
    )
}