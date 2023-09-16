package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.mommydndn.app.data.Preferences
import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.data.respository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val pref: Preferences
) : AccountRepository {
    override suspend fun signIn(tokenId: String, type: LoginType) {

        val request = when (type) {
            LoginType.GOOGLE -> LoginRequest(accessToken = tokenId, oauthProvider = "GOOGLE")
            LoginType.KAKAO -> LoginRequest(accessToken = tokenId, oauthProvider = "KAKAO")
            LoginType.NAVER -> LoginRequest(accessToken = tokenId, oauthProvider = "NAVER")
        }
        val response = apiService.login(request)

        if (response.isSuccessful) {
            val loginResponse = response.body()
            pref.putAccessToken(loginResponse?.accessToken)
            pref.putRefreshToken(loginResponse?.refreshToken)
        }
    }
}