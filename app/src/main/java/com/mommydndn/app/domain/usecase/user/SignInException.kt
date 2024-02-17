package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.domain.model.OAuthProvider

sealed class SignInException : Exception()

class UserNotFoundException(val token: String, val oAuthProvider: OAuthProvider) : SignInException()

class TokenNullException : SignInException()

class AuthCodeNullException : SignInException()