package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.NearestResponse
import com.mommydndn.app.data.model.SignUpRequest
import com.mommydndn.app.data.model.SignUpResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResponse<LoginResponse>

    @POST("/api/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): ApiResponse<SignUpResponse>

    @GET("/api/terms")
    suspend fun fetchTermsItems(
    ): ApiResponse<List<TermsItem>>

    @GET("/api/map/nearest")
    suspend fun fetchNearestByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<NearestResponse>

    @GET("/api/map/search")
    suspend fun fetchNearestByKeyword(
        @Query("keyword") keyword: String,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<NearestResponse>
}
