package com.mommydndn.app.domain.usecase

import com.mommydndn.app.utils.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<Result<R>> = execute(parameters)
        .map<R, Result<R>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { throwable -> emit(Result.Failure(Exception(throwable))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<R>
}

operator fun <R> FlowUseCase<Unit, R>.invoke(): Flow<Result<R>> = invoke(Unit)