package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.LoginType

interface AccountRepository {
    suspend fun logIn(tokenId: String, type: LoginType)
}