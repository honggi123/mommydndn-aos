package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.OAuthProvider
import com.mommydndn.app.domain.model.OAuthToken
import com.mommydndn.app.domain.model.UserType
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : UseCase<SignUpParams, OAuthToken>(coroutineDispatcher) {

    override suspend fun execute(parameters: SignUpParams): OAuthToken {
        TODO()
    }
}

data class SignUpParams(
    val oAuthProvider: OAuthProvider,
    val accessToken: String,
    val userType: UserType,
    val neighborhoodId: Int,
)

