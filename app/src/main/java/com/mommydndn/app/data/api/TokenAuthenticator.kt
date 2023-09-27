package com.mommydndn.app.data.api

import android.util.Log
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.api.service.AuthService
import com.mommydndn.app.data.datasource.TokenManager
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
            .addInterceptor(TokenInterceptor(tokenManager = tokenManager))
            .build()

        val authService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(AuthService::class.java)

        authService.reissue(authorizationHeader = refreshToken).onSuccess {
            accessToken = data.accessToken
        }

        return accessToken
    }


}