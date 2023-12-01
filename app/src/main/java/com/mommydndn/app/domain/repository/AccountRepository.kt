package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.network.model.response.LoginGoogleResponse
import com.mommydndn.app.data.network.model.response.LoginResponse
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.network.model.response.SignUpResponse
import com.skydoves.sandwich.ApiResponse

interface AccountRepository {

    suspend fun signIn(accessToken: String, OAuthProvider: OAuthProvider): ApiResponse<LoginResponse>

    suspend fun signUp(signUpInfo: SignUpInfo): ApiResponse<SignUpResponse>

    suspend fun getGoogleAccessToken(authCode: String): ApiResponse<LoginGoogleResponse>
}