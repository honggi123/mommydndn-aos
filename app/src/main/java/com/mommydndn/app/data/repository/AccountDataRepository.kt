package com.mommydndn.app.data.repository

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.network.feature.google.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.feature.user.request.SignInRequest
import com.mommydndn.app.data.network.feature.user.request.SignUpRequest
import com.mommydndn.app.data.network.feature.google.response.GetGoogleAccessTokenResponse
import com.mommydndn.app.data.network.feature.user.response.SignInResponse
import com.mommydndn.app.data.network.feature.auth.AuthService
import com.mommydndn.app.data.network.feature.google.GoogleApiService
import com.mommydndn.app.data.preferences.TokenManager
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.repository.AccountRepository
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val authService: AuthService,
    private val googleApiService: GoogleApiService,
    private val tokenManager: TokenManager
) : AccountRepository {

    override suspend fun signIn(
        acessToken: String,
        OAuthProvider: OAuthProvider
    ): SignInResponse {

        val response = authService
            .login(
                SignInRequest(
                    accessToken = acessToken,
                    oAuthProvider = OAuthProvider.name
                )
            )

        return response
    }

    override suspend fun signUp(
        accessToken: String,
        oAuthType: OAuthProvider,
        userType: UserType,
        emdId: Int
    ) = authService.signUp(
        SignUpRequest(
            accessToken = accessToken,
            oauthProvider = oAuthType.name,
            userType = userType.name,
            emdId = emdId
        )
    )

    override suspend fun saveUserToken(accessToken: String, refreshToken: String) {
        tokenManager.putAccessToken(accessToken)
        tokenManager.putRefreshToken(refreshToken)
    }

    override suspend fun getGoogleAccessToken(
        authCode: String
    ): GetGoogleAccessTokenResponse = googleApiService.getAccessToken(
        GetGoogleAccessTokenRequest(
            grant_type = "authorization_code",
            client_id = BuildConfig.GOOGLE_CLIENT_ID,
            client_secret = BuildConfig.GOOGLE_CLIENT_SECRET,
            code = authCode.orEmpty()
        )
    )
}
