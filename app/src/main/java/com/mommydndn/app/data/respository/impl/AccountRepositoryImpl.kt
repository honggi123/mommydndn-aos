package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.Preferences
import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.api.GoogleApiService
import com.mommydndn.app.data.model.LoginGoogleRequest
import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.data.respository.AccountRepository
import retrofit2.Response
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val googleApiService: GoogleApiService,
    private val pref: Preferences
) : AccountRepository {

    override suspend fun signIn(tokenId: String, type: LoginType): ApiResponse<LoginResponse> {
        val request = when (type) {
            LoginType.GOOGLE -> LoginRequest(accessToken = tokenId, oauthProvider = "GOOGLE")
            LoginType.KAKAO -> LoginRequest(accessToken = tokenId, oauthProvider = "KAKAO")
            LoginType.NAVER -> LoginRequest(accessToken = tokenId, oauthProvider = "NAVER")

        }
        val response = apiService.login(request)

        response.suspendOnSuccess {
            pref.putAccessToken(data?.accessToken)
            pref.putRefreshToken(data?.refreshToken)
        }

        return response
    }

    override suspend fun getGoogleAccesstoken(
        authCode: String,
        clientId: String,
        clientSecret: String
    ): Response<LoginGoogleResponse> = googleApiService.getAccessToken(
        LoginGoogleRequest(
            grant_type = "authorization_code",
            client_id = clientId,
            client_secret = clientSecret,
            code = authCode.orEmpty()
        )
    )


}