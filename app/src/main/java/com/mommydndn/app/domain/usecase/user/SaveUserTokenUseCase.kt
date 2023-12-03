package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.data.repository.AccountDataRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveUserTokenUseCase @Inject constructor(
    private val repository: AccountDataRepository,
) : UseCase<SaveTokenParams, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: SaveTokenParams) {
        return with(parameters) {
            repository.saveUserToken(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }
}

data class SaveTokenParams(
    val accessToken: String,
    val refreshToken: String
)
