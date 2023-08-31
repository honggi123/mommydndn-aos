package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
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

    companion object {
        private const val BASE_URL = "http://43.202.31.251"
        fun create(): ApiService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(ApiInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}