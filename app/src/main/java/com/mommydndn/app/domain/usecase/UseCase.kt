package com.mommydndn.app.domain.usecase

import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}

suspend operator fun <R> UseCase<Unit, R>.invoke(): Result<R> = invoke(Unit)