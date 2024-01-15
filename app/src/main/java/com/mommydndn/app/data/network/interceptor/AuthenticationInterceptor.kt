package com.mommydndn.app.data.network.interceptor

import com.mommydndn.app.data.preferences.PreferencesStorage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor(
    private val preferencesStorage: PreferencesStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().let { request ->
            val accessToken = preferencesStorage.getAccessToken()

            val urlString = request.url.toString()

            if (accessToken == null || shouldSkipAuthentication(urlString)) {
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

    private fun shouldSkipAuthentication(urlString: String): Boolean {
        val endpoints = listOf(
            "/api/terms",
            "/api/auth/signup",
            "/api/auth/login",
            "/api/map/search",
            "/api/map/nearest"
        )

        return endpoints.any { urlString.endsWith(it) }
    }
}
