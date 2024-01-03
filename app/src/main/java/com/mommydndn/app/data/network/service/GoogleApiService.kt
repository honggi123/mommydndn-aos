package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.request.GoogleLoginRequest
import com.mommydndn.app.data.network.model.response.LoginGoogleResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleApiService {

    @POST("oauth2/v4/token")
    suspend fun getAccessToken(@Body request: GoogleLoginRequest): LoginGoogleResponse
}
