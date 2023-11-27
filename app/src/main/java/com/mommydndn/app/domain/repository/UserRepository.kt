package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.OAuthToken

interface UserRepository {

    suspend fun getAccessToken(authCode: String): String

    // todo: store OAuthToken and get User
    suspend fun signIn(accessToken: String, oAuthProvider: OAuthProvider): OAuthToken

    // suspend fun updateProfileImage(..)
}