package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<SignInParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: SignInParams) {
        parameters.apply {
            repository.signIn(
                oauthProvider = oauthProvider,
                accessToken = accessToken,
                deviceToken = deviceToken
            )
        }
    }
}

data class SignInParams(
    val oauthProvider: OAuthProvider,
    val accessToken: String,
    val deviceToken: String
)