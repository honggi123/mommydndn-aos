package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.kakao.sdk.user.model.User
import com.mommydndn.app.data.api.model.request.UserProfileUpdateRequest
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.api.service.UserService
import com.mommydndn.app.data.respository.UserRepository
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val commonService: CommonService
) : UserRepository {
    override fun fetchUserInfo(): Flow<UserResponse> = flow {
        userService.fetchUserInfo().suspendOnSuccess {
            emit(data)
        }.onError { Log.e("onError", message()) }.onException { Log.e("onError", message()) }
    }.flowOn(Dispatchers.IO)

    override fun updateProfile(imagePart: MultipartBody.Part): Flow<Unit> = flow {
        val id = fetchImageId(imagePart)
        Log.e("id : ",id.toString() +"!")

        id?.let {
            userService.updateUserProfile(UserProfileUpdateRequest(imageId = id)).suspendOnSuccess {
                emit(data)
            }.onError { Log.e("onError", message()) }
                .onException { Log.e("onException", message()) }
        }
    }.flowOn(Dispatchers.IO)


    private suspend fun fetchImageId(imagePart: MultipartBody.Part): Int? =
        withContext(Dispatchers.IO) {
            commonService.fetchImageResponse(image = imagePart).getOrNull()?.imageId
        }

}