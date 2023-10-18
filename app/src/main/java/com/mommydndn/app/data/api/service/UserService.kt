package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.model.user.UserInfo
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface UserService {
    @GET("/api/user")
    suspend fun fetchUserInfo(): ApiResponse<UserInfo>
}