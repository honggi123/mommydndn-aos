package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.api.model.response.GetUserResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface UserRepository {

    suspend fun getUser(): GetUserResponse

    fun updateProfileImage(imagePart: MultipartBody.Part): Flow<Unit>
}