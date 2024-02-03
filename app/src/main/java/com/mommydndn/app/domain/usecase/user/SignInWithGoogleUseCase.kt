package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton


// 구글 auth server를 통해 응답 받은 인증 코드 (authorization code)
typealias GoogleAuthCode = String

@Singleton
class SignInWithGoogleUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<GoogleAuthCode, Unit>(coroutineDispatcher) {

    override suspend fun execute(authCode: GoogleAuthCode): Unit {
        val accessToken = repository.getGoogleAccessToken(authCode)

        repository.signIn(
            oauthProvider = OAuthProvider.Google,
            accessToken = accessToken
        )
    }
}

