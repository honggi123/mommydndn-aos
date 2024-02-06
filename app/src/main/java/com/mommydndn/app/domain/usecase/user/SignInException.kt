package com.mommydndn.app.domain.usecase.user

sealed class SignInException : Exception()

class UserNotFoundException(val token: String) : SignInException()

class TokenNullException : SignInException()