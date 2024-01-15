package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.OAuthToken
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
        return with(parameters) {
            repository.signUp(
                oAuthProvider = oAuthProvider,
                accessToken = accessToken,
                userType = userType,
                neighborhoodId = neighborhoodId,
            )
        }
    }
}

data class SignUpParams(
    val oAuthProvider: OAuthProvider,
    val accessToken: String,
    val userType: UserType,
    val neighborhoodId: Int,
)

