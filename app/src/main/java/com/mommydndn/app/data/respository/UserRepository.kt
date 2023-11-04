package com.mommydndn.app.data.respository

import com.mommydndn.app.data.api.model.response.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun fetchUserInfo(): Flow<UserResponse>

}