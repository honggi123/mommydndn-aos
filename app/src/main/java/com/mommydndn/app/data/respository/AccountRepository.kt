package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.LoginType
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response

interface AccountRepository {
    suspend fun signIn(tokenId: String, type: LoginType): ApiResponse<LoginResponse>

    suspend fun getGoogleAccesstoken(
        authCode: String,
        clientId: String,
        clientSecret: String
    ): Response<LoginGoogleResponse>
}