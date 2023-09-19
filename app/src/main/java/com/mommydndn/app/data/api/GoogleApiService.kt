package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.LoginGoogleRequest
import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {
    @POST("oauth2/v4/toke")
    suspend fun getAccessToken(
        @Body request: LoginGoogleRequest
    ): Response<LoginGoogleResponse>

    companion object {
        private const val BASE_URL = "https://www.googleapis.com"
        fun create(): GoogleApiService {
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
                .create(GoogleApiService::class.java)
        }
    }
}