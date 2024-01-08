package com.mommydndn.app.data.network.feature.google

import com.mommydndn.app.data.network.feature.google.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.feature.google.response.GetGoogleAccessTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {

    @POST("oauth2/v4/token")
    suspend fun fetchAccessToken(@Body request: GetGoogleAccessTokenRequest): GetGoogleAccessTokenResponse
}
