package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.OAuthProvider

interface UserRepository {

    suspend fun signIn(
        oauthProvider: OAuthProvider,
        accessToken: String,
    )

    suspend fun getGoogleAccessToken(
        authCode: String
    ): String

}