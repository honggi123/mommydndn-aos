package com.mommydndn.app.data.api

import com.mommydndn.app.data.datasource.TokenManager
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.toString()

        val accessToken = tokenManager.getAccessToken()

        val request =
            if (accessToken != null && !url.endsWith("api/terms")
                && !url.endsWith("/api/auth/signup")
                && !url.endsWith("/api/auth/login")
        ) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(request)
    }
}