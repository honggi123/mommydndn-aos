package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.LoginGoogleRequest
import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {
    @POST("oauth2/v4/token")
    suspend fun getAccessToken(
        @Body request: LoginGoogleRequest
    ): ApiResponse<LoginGoogleResponse>

}