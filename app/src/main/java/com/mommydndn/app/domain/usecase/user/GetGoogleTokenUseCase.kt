package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

// 구글 auth server를 통해 응답 받은 인증 코드 (authorization code)
typealias GoogleAuthCode = String?

@Singleton
class GetGoogleTokenUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<GoogleAuthCode, String>(coroutineDispatcher) {

    override suspend fun execute(authCode: GoogleAuthCode): String {
        if (authCode.isNullOrBlank()) {
            throw Exception("authCode null")
        } else {
            return repository.getGoogleAccessToken(authCode)
        }
    }
}