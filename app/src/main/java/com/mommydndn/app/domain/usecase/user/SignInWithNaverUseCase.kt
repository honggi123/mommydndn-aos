package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

typealias NaverAccessToken = String?

@Singleton
class SignInWithNaverUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<NaverAccessToken, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: NaverAccessToken) {
        if (parameters == null) {
            throw TokenNullException()
        } else {
            repository.signIn(
                oauthProvider = OAuthProvider.Naver,
                accessToken = parameters,
            )
        }
    }
}

