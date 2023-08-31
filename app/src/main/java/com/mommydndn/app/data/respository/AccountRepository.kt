package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.SignInType

interface AccountRepository {
    suspend fun signIn(tokenId: String, type: SignInType)
}