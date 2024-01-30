package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.model.NetworkOAuthProvider
import com.mommydndn.app.data.network.service.GoogleService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.data.network.service.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.request.SignInRequest
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.BuildConfig
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
        deviceToken: String
    ) {
        userService.signIn(
            SignInRequest(
                accessToken = accessToken,
                oauthProvider = oauthProvider.toNetwork(),
                deviceToken = deviceToken
            )
        )
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

    private fun OAuthProvider.toNetwork(): NetworkOAuthProvider {
        return when (this) {
            OAuthProvider.Google -> NetworkOAuthProvider.GOOGLE
            OAuthProvider.Naver -> NetworkOAuthProvider.NAVER
            OAuthProvider.Kakao -> NetworkOAuthProvider.KAKAO
            // TODO add enum apple provider
        }
    }

}

