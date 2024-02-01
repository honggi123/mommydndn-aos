package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInWithGoogleUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<SignInWithGoogleParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: SignInWithGoogleParams): Unit {
        val accessToken = repository.getGoogleAccessToken(parameters.authCode)

        repository.signIn(
            oauthProvider = OAuthProvider.Google,
            accessToken = accessToken
        )
    }
}

data class SignInWithGoogleParams(
    val authCode: String
)