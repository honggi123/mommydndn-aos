package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.kakao.sdk.user.model.User
import com.mommydndn.app.data.api.model.response.UserResponse
import com.mommydndn.app.data.api.service.UserService
import com.mommydndn.app.data.respository.UserRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl  @Inject constructor(
   private val userService: UserService
) : UserRepository {
    override fun fetchUserInfo(): Flow<UserResponse> = flow {
        userService.fetchUserInfo().suspendOnSuccess {
            emit(data)
        }.onError { Log.e("onError",message())  }.onException { Log.e("onError",message()) }
    }.flowOn(Dispatchers.IO)
}