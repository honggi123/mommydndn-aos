package com.mommydndn.app.data.network.feature.user

import com.mommydndn.app.data.api.model.response.GetNotificationSettingListResponse
import com.mommydndn.app.data.network.feature.user.request.SignInRequest
import com.mommydndn.app.data.network.feature.user.request.SignUpRequest
import com.mommydndn.app.data.network.feature.user.request.UpdateProfileImageRequest
import com.mommydndn.app.data.network.feature.user.response.GetUserResponse
import com.mommydndn.app.data.network.feature.user.response.SignInResponse
import com.mommydndn.app.data.network.feature.user.response.RefreshAccessTokenResponse
import com.mommydndn.app.data.network.feature.user.response.SignUpResponse
import com.mommydndn.app.data.network.feature.user.request.UpdateTosAgreementRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {

    @GET("/api/user")
    suspend fun fetchUser(): GetUserResponse

    @GET("/api/notice/setting")
    suspend fun fetchNotificationSettings(): GetNotificationSettingListResponse

    @POST("/api/auth/login")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse

    @POST("/api/auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): SignUpResponse

    @POST("/api/auth/reissue")
    suspend fun refreshAccessToken(): RefreshAccessTokenResponse

    @POST("api/user/terms")
    suspend fun updateTosAgreement(@Body request: UpdateTosAgreementRequest)

    @PUT("/api/user/profile-image")
    suspend fun updateProfileImage(@Body request: UpdateProfileImageRequest)

}

