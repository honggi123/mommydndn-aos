package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.auth.google.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.model.auth.google.request.GetGoogleAccessTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {

    @POST("oauth2/v4/token")
    suspend fun fetchAccessToken(@Body request: GetGoogleAccessTokenRequest): GetGoogleAccessTokenResponse
}
