package com.mommydndn.app.data.network.interceptor

import com.mommydndn.app.data.preferences.TokenManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().let { request ->
            val accessToken = tokenManager.getAccessToken()
            val urlString = request.url.toString()

            if (shouldSkipAuthentication(urlString, accessToken)) {
                request
            } else {
                request.newBuilder()
                    .addAuthHeader(accessToken)
                    .build()
            }
        }.let { request ->
            chain.proceed(request)
        }
    }

    private fun shouldSkipAuthentication(urlString: String, accessToken: String?): Boolean {
        val endpoints = listOf(
            "/api/terms",
            "/api/auth/signup",
            "/api/auth/login",
            "/api/map/search",
            "/api/map/nearest"
        )

        return accessToken == null || endpoints.any { urlString.endsWith(it) }
    }

    private fun Request.Builder.addAuthHeader(accessToken: String?): Request.Builder {
        return this.header("Authorization", "Bearer $accessToken")
    }
}
