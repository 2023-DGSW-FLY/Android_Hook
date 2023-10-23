package com.innosync.data.repository

import com.innosync.data.remote.mapper.toModel
import com.innosync.data.remote.request.UserGetTheseRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.service.ChatService
import com.innosync.data.remote.service.UserService
import com.innosync.data.remote.utiles.hookApiCall
import com.innosync.domain.model.UserModel
import com.innosync.domain.repository.ChatRepository
import com.innosync.domain.repository.UserRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService
): ChatRepository {
    override suspend fun getThese(users: List<String>): Map<String, String> = hookApiCall {
        chatService.getThese(users = UserGetTheseRequest(users)).data
    }

    override suspend fun getUserName(userId: String): String = hookApiCall {
        chatService.getUserName(userId).data.name
    }


}