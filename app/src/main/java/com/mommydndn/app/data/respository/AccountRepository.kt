package com.mommydndn.app.data.respository

import com.mommydndn.app.data.api.model.LoginGoogleResponse
import com.mommydndn.app.data.api.model.LoginResponse
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.api.model.SignUpResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun signIn(
        accessToken: String,
        oAuthType: OAuthType
    ): ApiResponse<LoginResponse>

    suspend fun signUp(
        signUpInfo: SignUpInfo,
    ): ApiResponse<SignUpResponse>

    suspend fun getGoogleAccesstoken(
        authCode: String
    ): ApiResponse<LoginGoogleResponse>
}