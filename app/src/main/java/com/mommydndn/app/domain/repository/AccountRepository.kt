package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.api.model.response.LoginGoogleResponse
import com.mommydndn.app.data.api.model.response.LoginResponse
import com.mommydndn.app.data.model.user.OAuthType
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.api.model.response.SignUpResponse
import com.skydoves.sandwich.ApiResponse

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