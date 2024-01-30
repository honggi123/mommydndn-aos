package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInWithNaverUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<SignInWithNaverParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: SignInWithNaverParams) {
        val deviceToken = repository.getFirebaseFcmToken()

        repository.signIn(
            oauthProvider = OAuthProvider.Naver,
            accessToken = parameters.accessToken,
            deviceToken = deviceToken
        )
    }
}

data class SignInWithNaverParams(
    val accessToken: String
)