package com.innosync.data.remote.firebase.mapper

import com.innosync.data.remote.firebase.response.RoomInfo
import com.innosync.domain.model.RoomData

internal fun RoomInfo.toModel() =
    RoomData(
        timestamp = timestamp,
        roomName = roomName,
        chatRoomUid = chatRoomUid,
        check = check,
        lastMessage = lastMessage,
        users = users,
        thumbnail = thumbnail,
        key = key
    )