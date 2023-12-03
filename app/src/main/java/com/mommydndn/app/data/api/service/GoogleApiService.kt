package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.GoogleLoginRequest
import com.mommydndn.app.data.api.model.response.LoginGoogleResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {

    @POST("oauth2/v4/token")
    suspend fun getAccessToken(@Body request: GoogleLoginRequest): ApiResponse<LoginGoogleResponse>
}
