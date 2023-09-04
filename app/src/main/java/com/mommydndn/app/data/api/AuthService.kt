package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/google")
    suspend fun loginGoogle(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/api/auth/kakao")
    suspend fun loginKakao(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/api/auth/naver")
    suspend fun loginNaver(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}