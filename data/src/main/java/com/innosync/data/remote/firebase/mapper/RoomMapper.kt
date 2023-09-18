package com.innosync.data.remote.firebase.mapper

import com.innosync.data.remote.firebase.response.RoomResponse
import com.innosync.domain.model.RoomData

internal fun RoomResponse.toModel() =
    RoomData(
        timestamp = timestamp.seconds,
        roomName = roomName,
        chatRoomUid = chatRoomUid,
        check = check,
        lastMessage = lastMessage,
        users = users
    )