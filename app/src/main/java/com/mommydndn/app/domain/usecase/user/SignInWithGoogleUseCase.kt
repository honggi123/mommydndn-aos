package com.mommydndn.app.domain.usecase.user

import android.util.Log
import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

typealias GoogleAuthCode = String?

@Singleton
class SignInWithGoogleUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<GoogleAuthCode, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: GoogleAuthCode) {
        if (parameters == null) {
            throw AuthCodeNullException()
        } else {
            val token = repository.getGoogleAccessToken(parameters)
            repository.signIn(
                oauthProvider = OAuthProvider.Google,
                accessToken = token ?: throw TokenNullException()
            )
        }
    }
}
