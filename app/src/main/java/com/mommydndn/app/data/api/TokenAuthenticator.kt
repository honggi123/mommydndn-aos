package com.mommydndn.app.data.api

import android.util.Log
import com.mommydndn.app.data.datasource.TokenManager
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnSuccess
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenManager: TokenManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.i("Authenticator", response.toString())
        Log.i("Authenticator", "토큰 재발급 시도")
        return try {
            var newAccessToken: String? = tokenManager.getRefreshToken()

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
}