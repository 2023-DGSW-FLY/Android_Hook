package com.innosync.data.remote.firebase.mapper

import com.innosync.data.remote.firebase.response.RoomResponse
import com.innosync.domain.model.RoomModel

internal fun RoomResponse.toModel() =
    RoomModel(
        timestamp = timestamp.seconds,
        roomName = roomName,
        chatRoomUid = chatRoomUid,
        check = check,
        lastMessage = lastMessage,
        users = users
    )