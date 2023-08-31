package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.mommydndn.app.data.Preferences
import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.SignInType
import com.mommydndn.app.data.respository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferences: Preferences
) : AccountRepository {
    override suspend fun signIn(tokenId: String, type: SignInType) {
        val request = LoginRequest(tokenId)
        val response = when (type) {
            SignInType.GOOGLE -> apiService.loginGoogle(request)
            SignInType.KAKAO -> apiService.loginKakao(request)
            SignInType.NAVER -> apiService.loginNaver(request)
        }

        if (response.isSuccessful) {
            val loginResponse = response.body()
            preferences.putAccessToken(loginResponse?.accessToken)
            preferences.putRefreshToken(loginResponse?.refreshToken)
        }
    }
}