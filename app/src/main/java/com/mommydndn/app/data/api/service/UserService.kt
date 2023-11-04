package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.UserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface UserService {
    @GET("/api/user")
    suspend fun fetchUserInfo(): ApiResponse<UserResponse>
}