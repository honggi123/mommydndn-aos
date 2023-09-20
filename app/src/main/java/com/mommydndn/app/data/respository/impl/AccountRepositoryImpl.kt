package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.Preferences
import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.api.GoogleApiService
import com.mommydndn.app.data.model.LoginGoogleRequest
import com.mommydndn.app.data.model.LoginGoogleResponse
import com.mommydndn.app.data.model.LoginRequest
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.model.SignUpRequest
import com.mommydndn.app.data.model.SignUpResponse
import com.mommydndn.app.data.model.UserType
import com.mommydndn.app.data.respository.AccountRepository
import retrofit2.Response
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val googleApiService: GoogleApiService,
    private val pref: Preferences
) : AccountRepository {

    override suspend fun signIn(
        acessToken: String,
        oAuthType: OAuthType
    ): ApiResponse<LoginResponse> {

        val response = apiService
            .login(
                LoginRequest(
                    accessToken = acessToken,
                    oauthProvider = oAuthType.apiValue
                )
            )
            .suspendOnSuccess {
                pref.putAccessToken(data?.accessToken)
                pref.putRefreshToken(data?.refreshToken)
            }

        return response
    }

    override suspend fun signUp(signUpInfo: SignUpInfo): ApiResponse<SignUpResponse> {
        val response = apiService.signUp(
            SignUpRequest(
                accessToken = signUpInfo.accessToken ?: "",
                oauthProvider = signUpInfo.oAuthType!!.apiValue,
                userType = signUpInfo.userType!!.apiValue,
                emdId = signUpInfo.emdId ?: 0
            )
        ).suspendOnSuccess {
            pref.putAccessToken(data?.accessToken)
            pref.putRefreshToken(data?.refreshToken)
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