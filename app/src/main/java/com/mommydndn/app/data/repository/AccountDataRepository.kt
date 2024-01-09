package com.mommydndn.app.data.repository

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.network.service.google.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.user.request.SignInRequest
import com.mommydndn.app.data.network.service.user.request.SignUpRequest
import com.mommydndn.app.data.network.service.google.response.GetGoogleAccessTokenResponse
import com.mommydndn.app.data.network.service.user.response.SignInResponse
import com.mommydndn.app.data.network.service.auth.AuthService
import com.mommydndn.app.data.network.service.google.GoogleApiService
import com.mommydndn.app.data.network.service.user.UserService
import com.mommydndn.app.data.network.service.user.response.SignUpResponse
import com.mommydndn.app.data.preferences.TokenManager
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.repository.AccountRepository
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val userService: UserService,
    private val googleApiService: GoogleApiService,
    private val tokenManager: TokenManager
) : AccountRepository {

    override suspend fun signIn(
        acessToken: String,
        OAuthProvider: OAuthProvider
    ): SignInResponse {
//        return userService.signIn(
//            SignInRequest(
//                accessToken = acessToken,
//                oAuthProvider = OAuthProvider
//            )
//        )
        TODO()
    }

    override suspend fun signUp(
        accessToken: String,
        oAuthType: OAuthProvider,
        userType: UserType,
        emdId: Int
    ): SignUpResponse {
//       return userService.signUp(
//            SignUpRequest(
//                accessToken = accessToken,
//                oauthProvider = oAuthType.name,
//                userType = userType,
//                locationId = emdId
//            )
//        )
        TODO()
    }

    override suspend fun saveUserToken(accessToken: String, refreshToken: String) {
        tokenManager.putAccessToken(accessToken)
        tokenManager.putRefreshToken(refreshToken)
    }

    override suspend fun getGoogleAccessToken(
        authCode: String
    ): GetGoogleAccessTokenResponse = googleApiService.fetchAccessToken(
        GetGoogleAccessTokenRequest(
            grantType = "authorization_code",
            clientId = BuildConfig.GOOGLE_CLIENT_ID,
            clientSecret = BuildConfig.GOOGLE_CLIENT_SECRET,
            code = authCode.orEmpty()
        )
    )
}
