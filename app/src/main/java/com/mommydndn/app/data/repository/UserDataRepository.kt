package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.GoogleService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.data.network.service.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.request.SignInRequest
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.mapper.transformToOAuthProvider
import com.mommydndn.app.domain.usecase.user.UserNotFoundException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userService: UserService,
    private val googleService: GoogleService
) : UserRepository {

    override suspend fun signIn(
        oauthProvider: OAuthProvider,
        accessToken: String,
    ) {
        try {
            userService.signIn(
                SignInRequest(
                    accessToken = accessToken,
                    oauthProvider = oauthProvider.transformToOAuthProvider(),
                )
            )
        } catch (exception: Exception) {
            val transformedException = when (exception) {
                is HttpException -> when (exception.code()) {
                    403 -> UserNotFoundException(accessToken)
                    else -> exception
                }

                else -> exception
            }
            throw (transformedException)
        }
    }

    override suspend fun getGoogleAccessToken(
        authCode: String
    ): String {
        val result = googleService.getAccessToken(
            GetGoogleAccessTokenRequest(
                clientId = BuildConfig.GOOGLE_CLIENT_ID,
                clientSecret = BuildConfig.GOOGLE_CLIENT_SECRET,
                grantType = "authorization_code",
                code = authCode
            )
        )
        return result.accessToken
    }

}

