package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.data.api.model.response.SignUpResponse
import com.mommydndn.app.data.repository.AccountDataRepository
import com.mommydndn.app.domain.model.user.OAuthType
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpUseCase @Inject constructor(
    private val repository: AccountDataRepository,
) : UseCase<SignUpParams, SignUpResponse>(Dispatchers.IO) {

    override suspend fun execute(parameters: SignUpParams): SignUpResponse {
        return with(parameters) {
            repository.signUp(
                accessToken = accessToken,
                oAuthType = oAuthType,
                userType = userType,
                emdId = emdId
            )
        }
    }
}

data class SignUpParams(
    val accessToken: String,
    val oAuthType: OAuthType,
    val userType: UserType,
    val emdId: Int
)
