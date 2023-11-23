package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.UserProfileUpdateRequest
import com.mommydndn.app.data.api.model.response.UserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserService {

    @GET("/api/user")
    suspend fun fetchUserInfo(): ApiResponse<UserResponse>

    @PUT("/api/user/profile-image")
    suspend fun updateUserProfile(
        @Body userProfileUpdateRequest: UserProfileUpdateRequest
    ): ApiResponse<Unit>
}

