package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<SignInParams, Unit>(coroutineDispatcher) { // todo: return ..?

    override suspend fun execute(parameters: SignInParams) {
        return with(parameters) {
            repository.signIn(oAuthProvider, accessToken)
        }
    }
}

data class SignInParams(
    val oAuthProvider: OAuthProvider,
    val accessToken: String
)