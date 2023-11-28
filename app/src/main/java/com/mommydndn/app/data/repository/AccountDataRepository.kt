package com.mommydndn.app.data.repository

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.api.service.AuthenticationService
import com.mommydndn.app.data.api.service.GoogleApiService
import com.mommydndn.app.data.preferences.TokenManager
import com.mommydndn.app.data.api.model.request.GoogleLoginRequest
import com.mommydndn.app.data.api.model.response.LoginGoogleResponse
import com.mommydndn.app.data.api.model.request.SignInRequest
import com.mommydndn.app.data.api.model.response.LoginResponse
import com.mommydndn.app.domain.model.user.OAuthType
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.api.model.request.SignUpRequest
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.repository.AccountRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val googleApiService: GoogleApiService,
    private val tokenManager: TokenManager
) : AccountRepository {

    override suspend fun signIn(
        acessToken: String,
        oAuthType: OAuthType
    ): ApiResponse<LoginResponse> {
        val response = authenticationService
            .login(
                SignInRequest(
                    accessToken = acessToken,
                    oauthProvider = oAuthType.name
                )
            )
            .suspendOnSuccess {
                tokenManager.putAccessToken(data?.accessToken)
                tokenManager.putRefreshToken(data?.refreshToken)
            }

        return response
    }

    override suspend fun signUp(
        accessToken: String,
        oAuthType: OAuthType,
        userType: UserType,
        emdId: Int
    ) = authenticationService.signUp(
            SignUpRequest(
                accessToken = accessToken,
                oauthProvider = oAuthType.name,
                userType = userType.name,
                emdId = emdId
            )
        ).suspendOnSuccess {
            tokenManager.putAccessToken(data?.accessToken)
            tokenManager.putRefreshToken(data?.refreshToken)
        }


    override suspend fun getGoogleAccessToken(
        authCode: String
    ): ApiResponse<LoginGoogleResponse> = googleApiService.getAccessToken(
        GoogleLoginRequest(
            grant_type = "authorization_code",
            client_id = BuildConfig.GOOGLE_CLIENT_ID,
            client_secret = BuildConfig.GOOGLE_CLIENT_SECRET,
            code = authCode.orEmpty()
        )
    )


}