package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.auth.request.SignInRequest
import com.mommydndn.app.data.network.model.auth.request.SignUpRequest
import com.mommydndn.app.data.network.model.user.response.SignInResponse
import com.mommydndn.app.data.network.model.auth.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.network.model.user.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationService {

    @POST("/api/auth/reissue")
    suspend fun reissue(@Header("Authorization") authorizationHeader: String): RefreshAccessTokenResponse
}