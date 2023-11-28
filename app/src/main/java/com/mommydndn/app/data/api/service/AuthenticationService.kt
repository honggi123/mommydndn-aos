package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.SignInRequest
import com.mommydndn.app.data.api.model.response.LoginResponse
import com.mommydndn.app.data.api.model.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.api.model.request.SignUpRequest
import com.mommydndn.app.data.api.model.response.SignUpResponse
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
    ): SignUpResponse

    @POST("/api/auth/reissue")
    suspend fun reissue(
        @Header("Authorization") authorizationHeader: String
    ): ApiResponse<RefreshAccessTokenResponse>
}