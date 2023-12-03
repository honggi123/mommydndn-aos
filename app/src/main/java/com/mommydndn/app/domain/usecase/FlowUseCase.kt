package com.mommydndn.app.domain.usecase

import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .map { Result.Success(it) }
        .catch { throwable -> emitError(throwable) }
        .flowOn(coroutineDispatcher)

    private fun emitError(throwable: Throwable): Flow<Result<R>> = flow { Result.Failure(exception = Exception(throwable)) }

    protected abstract suspend fun execute(parameters: P): Flow<R>
}
