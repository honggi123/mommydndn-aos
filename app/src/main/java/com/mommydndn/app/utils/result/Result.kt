package com.mommydndn.app.utils.result

sealed class Result<out R> {

    data object Loading: Result<Nothing>()

    data class Success<out T>(val data: T): Result<T>()

    data class Failure(val exception: Exception): Result<Nothing>()
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data