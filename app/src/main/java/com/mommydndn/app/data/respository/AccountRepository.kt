package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.LoginType

interface AccountRepository {
    suspend fun signIn(tokenId: String, type: LoginType)
}