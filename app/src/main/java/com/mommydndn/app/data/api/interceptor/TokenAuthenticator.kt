package com.mommydndn.app.data.api.interceptor

import android.util.Log
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.api.service.AuthenticationService
import com.mommydndn.app.data.preferences.TokenManager
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TokenAuthenticator(
    private val tokenManager: TokenManager,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.i("Authenticator", response.toString())
        Log.i("Authenticator", "토큰 재발급 시도")
        return try {
            var refreshToken: String? = tokenManager.getRefreshToken()

            val newAccessToken = runBlocking {
                refreshToken?.let { getNewAccessToken(it) }
            }

            tokenManager.putAccessToken(newAccessToken)

            if (newAccessToken != null) {
                Log.i("Authenticator", "토큰 재발급 성공 : $newAccessToken")
                response.request.newBuilder()
                    .removeHeader("Authorization").apply {
                        addHeader("Authorization", newAccessToken)
                    }.build()
            } else {
                null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend inline fun getNewAccessToken(
        refreshToken: String,
    ): String? {
        var accessToken: String? = null

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor(tokenManager = tokenManager))
            .build()

        val authenticationService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(AuthenticationService::class.java)

        authenticationService.reissue(authorizationHeader = refreshToken).onSuccess {
            accessToken = data.accessToken
        }

        return accessToken
    }


}