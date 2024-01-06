package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.auth.request.SignInRequest
import com.mommydndn.app.data.network.model.auth.request.SignUpRequest
import com.mommydndn.app.data.network.model.user.request.UpdateProfileImageRequest
import com.mommydndn.app.data.network.model.user.response.GetUserResponse
import com.mommydndn.app.data.network.model.user.response.SignInResponse
import com.mommydndn.app.data.network.model.auth.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.network.model.user.response.SignUpResponse
import com.mommydndn.app.data.network.model.tos.UpdateTosAgreementRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {

    @POST("/api/auth/login")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse

    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @POST("/api/auth/reissue")
    suspend fun refreshAccessToken(): RefreshAccessTokenResponse

    @POST("api/user/terms")
    suspend fun updateTosAgreement(@Body request: UpdateTosAgreementRequest)

    @GET("/api/user")
    suspend fun getUser(): GetUserResponse

    @PUT("/api/user/profile-image")
    suspend fun updateProfileImage(@Body request: UpdateProfileImageRequest)
}

