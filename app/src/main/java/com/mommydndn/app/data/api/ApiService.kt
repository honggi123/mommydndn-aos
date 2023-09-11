package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResponse<LoginResponse>


}