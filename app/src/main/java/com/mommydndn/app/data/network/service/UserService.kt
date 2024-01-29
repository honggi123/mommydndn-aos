package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkUser
import com.mommydndn.app.data.network.service.request.SignInRequest
import com.mommydndn.app.data.network.service.request.SignUpRequest
import com.mommydndn.app.data.network.service.request.UpdateProfileImageRequest
import com.mommydndn.app.data.network.service.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.network.service.response.SignInResponse
import com.mommydndn.app.data.network.service.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {

    @GET("/api/user")
    suspend fun getUser(): NetworkUser

    @POST("/api/auth/login")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse

    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @POST("/api/auth/reissue")
    suspend fun refreshAccessToken(): RefreshAccessTokenResponse

    @PUT("/api/user/profile-image")
    suspend fun updateProfileImage(@Body request: UpdateProfileImageRequest)
}

