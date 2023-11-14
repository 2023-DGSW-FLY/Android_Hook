package com.innosync.data.repository

import com.innosync.data.local.dao.FirebaseTokenDao
import com.innosync.data.local.dao.TokenDao
import com.innosync.data.local.entity.token.FirebaseTokenEntity
import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.data.local.mapper.toModel
import com.innosync.data.remote.request.TokenRequest
import com.innosync.data.remote.service.LoginService
import com.innosync.data.remote.service.TokenService
import com.innosync.domain.model.FirebaseTokenModel
import com.innosync.domain.model.Token
import com.innosync.domain.repository.FirebaseTokenRepository
import com.innosync.domain.repository.TokenRepository
import java.lang.Exception
import javax.inject.Inject

class FirebaseTokenRepositoryImpl @Inject constructor(
    private val firebaseTokenDao: FirebaseTokenDao
): FirebaseTokenRepository {
    override suspend fun getToken(): FirebaseTokenModel =
        firebaseTokenDao.getToken().toModel()

    override suspend fun insert(token: String) {
        firebaseTokenDao.insert(FirebaseTokenEntity(
            token = token
        ))
    }

//    override suspend fun fetchToken(): Token =
//        tokenDao.getToken().refreshToken.let { refreshToken ->
//            tokenService.token(TokenRequest(refreshToken)).data.accessToken.let { accessToken ->
//                tokenDao.insert(
//                    TokenEntity(
//                        token = accessToken,
//                        refreshToken = refreshToken
//                    )
//                ).let {
//                    Token(accessToken, refreshToken)
//                }
//            }
//        }
//
//    override suspend fun deleteToken() =
//        tokenDao.deleteToken()
//
//    override suspend fun checkToken(): Boolean {
//        return try {
//            tokenDao.getToken().token.let {
//                it.isNotEmpty()
//
//            }
//        } catch (e: Exception) {
//            false
//        }
//    }
}