package com.mommydndn.app.data.network.service.google

import com.mommydndn.app.data.network.service.google.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.google.response.GetGoogleAccessTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {
    @POST("oauth2/v4/token")
    suspend fun fetchAccessToken(@Body request: GetGoogleAccessTokenRequest): GetGoogleAccessTokenResponse
}
