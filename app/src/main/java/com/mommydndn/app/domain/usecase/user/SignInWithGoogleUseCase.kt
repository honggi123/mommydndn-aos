package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

typealias GoogleAccessToken = String?

@Singleton
class SignInWithGoogleUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<GoogleAccessToken, Unit>(coroutineDispatcher) {

    override suspend fun execute(token: GoogleAccessToken): Unit {
        if (token == null) {
            throw TokenNullException()
        } else {
            repository.signIn(
                oauthProvider = OAuthProvider.Google,
                accessToken = token
            )
        }
    }
}

