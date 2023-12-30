package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.request.SignInRequest
import com.mommydndn.app.data.network.model.request.SignUpRequest
import com.mommydndn.app.data.network.model.request.UpdateProfileImageRequest
import com.mommydndn.app.data.network.model.request.UpdateTermsOfServiceListRequest
import com.mommydndn.app.data.network.model.response.GetUserResponse
import com.mommydndn.app.data.network.model.response.LoginResponse
import com.mommydndn.app.data.network.model.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.network.model.response.SignUpResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {

    @POST("/api/auth/login")
    suspend fun signIn(@Body request: SignInRequest): ApiResponse<LoginResponse>

    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): ApiResponse<SignUpResponse>

    @POST("/api/auth/reissue")
    suspend fun refreshAccessToken(): ApiResponse<RefreshAccessTokenResponse>

    @POST("api/user/terms")
    suspend fun updateTos(@Body request: UpdateTermsOfServiceListRequest)

    @GET("/api/user")
    suspend fun getUser(): GetUserResponse

    @PUT("/api/user/profile-image")
    suspend fun updateProfileImage(@Body request: UpdateProfileImageRequest): ApiResponse<Unit>
}

