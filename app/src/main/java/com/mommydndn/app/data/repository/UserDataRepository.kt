package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.GoogleService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.data.network.service.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.request.SignInRequest
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.mapper.toOAuthProvider
import com.mommydndn.app.data.preferences.PreferencesStorage
import com.mommydndn.app.domain.usecase.user.TokenNullException
import com.mommydndn.app.domain.usecase.user.UserNotFoundException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userService: UserService,
    private val googleService: GoogleService,
    private val preferencesStorage: PreferencesStorage
) : UserRepository {

    override suspend fun signIn(
        oauthProvider: OAuthProvider,
        accessToken: String,
    ) {
        try {
            userService.signIn(
                SignInRequest(
                    accessToken = accessToken,
                    oauthProvider = oauthProvider.toOAuthProvider(),
                )
            ).let { result ->
                preferencesStorage.run {
                    setAccessToken(result.accessToken)
                    setRefreshToken(result.refreshToken)
                }
            }
        } catch (exception: Exception) {
            val transformedException =
                if (exception is HttpException && exception.code() == 403) {
                    UserNotFoundException(accessToken, oauthProvider)
                } else {
                    exception
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
        return result.accessToken ?: throw TokenNullException()
    }

}

