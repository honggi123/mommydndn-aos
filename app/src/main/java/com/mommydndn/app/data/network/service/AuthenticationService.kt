package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.request.SignInRequest
import com.mommydndn.app.data.network.model.response.LoginResponse
import com.mommydndn.app.data.network.model.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.network.model.request.SignUpRequest
import com.mommydndn.app.data.network.model.response.SignUpResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationService {

    @POST("/api/auth/login")
    suspend fun login(
        @Body signInRequest: SignInRequest
    ): ApiResponse<LoginResponse>

    @POST("/api/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): ApiResponse<SignUpResponse>

    @POST("/api/auth/reissue")
    suspend fun reissue(
        @Header("Authorization") authorizationHeader: String
    ): ApiResponse<RefreshAccessTokenResponse>
}