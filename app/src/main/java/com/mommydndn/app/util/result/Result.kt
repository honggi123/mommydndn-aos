package com.mommydndn.app.util.result

import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.Exception

sealed class Result<out R> {

    object Loading: Result<Nothing>()

    data class Success<out T>(val data: T): Result<T>()

    data class Failure(val exception: Exception): Result<Nothing>()
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

inline fun <reified T> Result<T>.updateOnSuccess(stateFlow: MutableStateFlow<T>) {
    if (this is Result.Success) {
        stateFlow.value = data
    }
}