package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.auth.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.model.auth.request.GetGoogleAccessTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {

    @POST("oauth2/v4/token")
    suspend fun getAccessToken(@Body request: GetGoogleAccessTokenRequest): GetGoogleAccessTokenResponse
}
