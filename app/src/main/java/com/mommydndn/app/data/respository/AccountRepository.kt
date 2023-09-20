package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.model.SignUpResponse
import com.mommydndn.app.data.model.UserType
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response

interface AccountRepository {
    suspend fun signIn(
        accessToken: String,
        oAuthType: OAuthType
    ): ApiResponse<LoginResponse>

    suspend fun signUp(
        signUpInfo: SignUpInfo
    ): ApiResponse<SignUpResponse>

    suspend fun getGoogleAccesstoken(
        authCode: String
    ): ApiResponse<LoginGoogleResponse>
}