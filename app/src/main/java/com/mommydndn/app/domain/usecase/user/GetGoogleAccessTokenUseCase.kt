package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGoogleAccessTokenUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<SignInWithGoogleParams, String>(coroutineDispatcher) {

    override suspend fun execute(parameters: SignInWithGoogleParams): String {
       return repository.getGoogleAccessToken(parameters.authCode)
    }
}

data class SignInWithGoogleParams(
    val authCode: String
)