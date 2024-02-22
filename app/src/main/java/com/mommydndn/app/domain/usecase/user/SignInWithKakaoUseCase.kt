package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

typealias KakaoAccessToken = String

@Singleton
class SignInWithKakaoUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<KakaoAccessToken, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: KakaoAccessToken) {
        repository.signIn(
            oauthProvider = OAuthProvider.Kakao,
            accessToken = parameters
        )
    }
}
