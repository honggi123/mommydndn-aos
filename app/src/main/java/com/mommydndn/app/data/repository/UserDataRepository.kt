package com.mommydndn.app.data.repository

import android.util.Log
import com.mommydndn.app.data.api.model.request.UpdateProfileImageRequest
import com.mommydndn.app.data.api.model.response.GetUserResponse
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.api.service.UserService
import com.mommydndn.app.domain.repository.UserRepository
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

class UserDataRepository @Inject constructor(
    private val userService: UserService,
    private val commonService: CommonService,
) : UserRepository {

    override suspend fun getUser(): GetUserResponse {
        return userService.getUser()
    }

    override fun updateProfileImage(imagePart: MultipartBody.Part): Flow<Unit> = flow {
        val id = fetchImageId(imagePart)
        Log.e("id : ",id.toString() +"!")

        id?.let {
            userService.updateProfileImage(UpdateProfileImageRequest(imageId = id)).suspendOnSuccess {
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