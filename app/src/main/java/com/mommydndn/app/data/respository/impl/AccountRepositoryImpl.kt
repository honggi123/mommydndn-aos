package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.api.AuthService
import com.mommydndn.app.data.api.GoogleApiService
import com.mommydndn.app.data.datasource.TokenManager
import com.mommydndn.app.data.api.model.LoginGoogleRequest
import com.mommydndn.app.data.api.model.LoginGoogleResponse
import com.mommydndn.app.data.api.model.LoginRequest
import com.mommydndn.app.data.api.model.LoginResponse
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.api.model.SignUpRequest
import com.mommydndn.app.data.api.model.SignUpResponse
import com.mommydndn.app.data.respository.AccountRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val googleApiService: GoogleApiService,
    private val tokenManager: TokenManager
) : AccountRepository {

    override suspend fun signIn(
        acessToken: String,
        oAuthType: OAuthType
    ): ApiResponse<LoginResponse> {

        val response = authService
            .login(
                LoginRequest(
                    accessToken = acessToken,
                    oauthProvider = oAuthType.apiValue
                )
            )
            .suspendOnSuccess {
                tokenManager.putAccessToken(data?.accessToken)
                tokenManager.putRefreshToken(data?.refreshToken)
            }

        return response
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): ApiResponse<SignUpResponse> {

        val response = authService.signUp(
            SignUpRequest(
                accessToken = signUpInfo.accessToken ?: "",
                oauthProvider = signUpInfo.oAuthType?.apiValue ?: "",
                userType = signUpInfo.userType?.apiValue ?: "",
                emdId = signUpInfo.emdId ?: 0
            )
        ).suspendOnSuccess {
            tokenManager.putAccessToken(data?.accessToken)
            tokenManager.putRefreshToken(data?.refreshToken)
        }

        return response
    }

    override suspend fun getGoogleAccesstoken(
        authCode: String
    ): ApiResponse<LoginGoogleResponse> = googleApiService.getAccessToken(
        LoginGoogleRequest(
            grant_type = "authorization_code",
            client_id = BuildConfig.GOOGLE_CLIENT_ID,
            client_secret = BuildConfig.GOOGLE_CLIENT_SECRET,
            code = authCode.orEmpty()
        )
    )


}