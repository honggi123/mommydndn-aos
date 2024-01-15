package com.mommydndn.app.data.repository

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.network.service.GoogleService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.data.network.service.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.response.GetGoogleAccessTokenResponse
import com.mommydndn.app.data.network.service.user.response.SignInResponse
import com.mommydndn.app.data.network.service.user.response.SignUpResponse
import com.mommydndn.app.data.preferences.PreferencesStorage
import com.mommydndn.app.domain.model.UserType
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.repository.AccountRepository
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val userService: UserService,
    private val googleService: GoogleService,
    private val preferencesStorage: PreferencesStorage
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
        preferencesStorage.setAccessToken(accessToken)
        preferencesStorage.setRefreshToken(refreshToken)
    }

    override suspend fun getGoogleAccessToken(
        authCode: String
    ): GetGoogleAccessTokenResponse = googleService.getAccessToken(
        GetGoogleAccessTokenRequest(
            grantType = "authorization_code",
            clientId = BuildConfig.GOOGLE_CLIENT_ID,
            clientSecret = BuildConfig.GOOGLE_CLIENT_SECRET,
            code = authCode.orEmpty()
        )
    )
}
