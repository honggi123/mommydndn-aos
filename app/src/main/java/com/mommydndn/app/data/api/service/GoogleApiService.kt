package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.LoginGoogleRequest
import com.mommydndn.app.data.api.model.LoginGoogleResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {
    @POST("oauth2/v4/token")
    suspend fun getAccessToken(
        @Body request: LoginGoogleRequest
    ): ApiResponse<LoginGoogleResponse>

}