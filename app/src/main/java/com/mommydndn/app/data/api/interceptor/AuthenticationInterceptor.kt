package com.mommydndn.app.data.api.interceptor

import com.mommydndn.app.data.preferences.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().let { request ->
            val accessToken = tokenManager.getAccessToken()
            val urlString = request.url.toString()

            if (accessToken == null ||
                urlString.endsWith("/api/terms") ||
                urlString.endsWith("/api/auth/signup") ||
                urlString.endsWith("/api/auth/login")
            ) {
                request
            } else {
                request.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
            }
        }.let { request ->
            chain.proceed(request)
        }
    }
}
