package com.mommydndn.app.data.respository

import com.mommydndn.app.data.api.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UserRepository {
    fun fetchUserInfo(): Flow<UserResponse>

    fun updateProfile(
        imagePart: MultipartBody.Part
    ): Flow<Unit>
}