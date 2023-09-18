package com.innosync.data.remote.firebase.mapper

import com.google.firebase.Timestamp
import com.innosync.data.remote.firebase.response.ChatResponse
import com.innosync.domain.model.ChatData

internal fun List<ChatResponse>.toModels() =
    this.map { it.toModel() }

internal fun ChatResponse.toModel() =
    ChatData(
        author = author,
        content = content,
        timestamp = timestamp?.seconds ?: Timestamp.now().seconds
    )
