package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.data.network.model.response.SignUpResponse
import com.mommydndn.app.data.repository.AccountDataRepository
import com.mommydndn.app.domain.model.user.OAuthProvider
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

class SignUpUseCase constructor(
    private val repository: AccountDataRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<SignUpParams, SignUpResponse>(coroutineDispatcher) {

    override suspend fun execute(parameters: SignUpParams): SignUpResponse {
        return with(parameters) {
            if (oAuthType != null && accessToken.isNullOrBlank() && userType != null && emdId != null) {
                throw Exception("회원가입에 실패 했습니다.")
            } else {
                repository.signUp(
                    accessToken = accessToken,
                    oAuthType = oAuthType,
                    userType = userType,
                    emdId = emdId
                )
            }
        }
    }
}

data class SignUpParams(
    val accessToken: String,
    val oAuthType: OAuthProvider,
    val userType: UserType,
    val emdId: Int
)

