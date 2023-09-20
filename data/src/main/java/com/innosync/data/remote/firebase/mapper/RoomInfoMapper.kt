package com.innosync.data.remote.firebase.mapper

import com.innosync.data.remote.firebase.response.RoomInfo
import com.innosync.domain.model.RoomModel

internal fun RoomInfo.toModel() =
    RoomModel(
        timestamp = timestamp,
        roomName = roomName,
        chatRoomUid = chatRoomUid,
        check = check,
        lastMessage = lastMessage,
        users = users,
    )