package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveUserTokenUseCase @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<SaveTokenParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: SaveTokenParams) {
        TODO()
    }
}

data class SaveTokenParams(
    val accessToken: String,
    val refreshToken: String
)
