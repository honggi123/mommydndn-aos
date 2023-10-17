package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.JobOffer
import com.mommydndn.app.data.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun fetchUserInfo(): Flow<UserInfo>

}