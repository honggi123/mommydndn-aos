package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.ReissueResponse
import com.mommydndn.app.data.model.SignUpRequest
import com.mommydndn.app.data.model.SignUpResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResponse<LoginResponse>

    @POST("/api/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): ApiResponse<SignUpResponse>

    @POST("/api/auth/reissue")
    suspend fun reissue(
        @Header("Authorization") authorizationHeader: String
    ): ApiResponse<ReissueResponse>
}