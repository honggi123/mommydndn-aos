package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.request.SignInRequest
import com.mommydndn.app.data.api.service.UserService
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.OAuthToken
import com.mommydndn.app.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getAccessToken(authCode: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(
        accessToken: String,
        oAuthProvider: OAuthProvider
    ): OAuthToken {
        userService.signIn(SignInRequest(accessToken, oAuthProvider.name))
        TODO("Not yet implemented")
    }
}