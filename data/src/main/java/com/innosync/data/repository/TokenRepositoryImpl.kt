package com.innosync.data.repository

import android.util.Log
import com.innosync.data.local.dao.TokenDao
import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.data.local.mapper.toModel
import com.innosync.data.remote.request.TokenRequest
import com.innosync.data.remote.service.LoginService
import com.innosync.data.remote.service.TokenService
import com.innosync.domain.model.Token
import com.innosync.domain.repository.TokenRepository
import java.lang.Exception
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenDao: TokenDao,
    private val tokenService: TokenService
): TokenRepository {
    override suspend fun getToken(): Token =
        tokenDao.getToken().toModel()

    override suspend fun fetchToken(): Token =
        tokenDao.getToken().refreshToken.let { refreshToken ->
            tokenService.token(TokenRequest(refreshToken)).data.accessToken.let { accessToken ->
                Log.d("로그", "fetchToken: $accessToken")
                tokenDao.insert(
                    TokenEntity(
                        token = accessToken,
                        refreshToken = refreshToken
                    )
                ).let {
                    Token(accessToken, refreshToken)
                }
            }
        }

    override suspend fun deleteToken() =
        tokenDao.deleteToken()

    override suspend fun checkToken(): Boolean {
        return try {
            tokenDao.getToken().token.let {
                it.isNotEmpty()

            }
        } catch (e: Exception) {
            false
        }
    }
}