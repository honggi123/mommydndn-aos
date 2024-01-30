package com.mommydndn.app.data.repository

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.mommydndn.app.data.network.model.NetworkOAuthProvider
import com.mommydndn.app.data.network.service.GoogleService
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.data.network.service.request.GetGoogleAccessTokenRequest
import com.mommydndn.app.data.network.service.request.SignInRequest
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.BuildConfig
import com.mommydndn.app.data.mapper.toNetwork
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class UserDataRepository @Inject constructor(
    private val userService: UserService,
    private val googleService: GoogleService
) : UserRepository {

    override suspend fun signIn(
        oauthProvider: OAuthProvider,
        accessToken: String,
        deviceToken: String?
    ) {
        userService.signIn(
            SignInRequest(
                accessToken = accessToken,
                oauthProvider = oauthProvider.toNetwork(),
                deviceToken = deviceToken
            )
        )
    }

    override suspend fun getGoogleAccessToken(
        authCode: String
    ): String {
        val result = googleService.getAccessToken(
            GetGoogleAccessTokenRequest(
                clientId = BuildConfig.GOOGLE_CLIENT_ID,
                clientSecret = BuildConfig.GOOGLE_CLIENT_SECRET,
                grantType = "authorization_code",
                code = authCode
            )
        )
        return result.accessToken
    }

    override suspend fun getFirebaseFcmToken(): String? {
        return suspendCancellableCoroutine { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    continuation.resume(null)
                    return@OnCompleteListener
                }
                continuation.resume(task.result)
            }).addOnFailureListener { exception ->
                continuation.resume(null)
            }
        }
    }
}

