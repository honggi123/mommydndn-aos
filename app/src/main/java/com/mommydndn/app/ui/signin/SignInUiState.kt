package com.mommydndn.app.ui.signin

import com.mommydndn.app.domain.model.user.OAuthProvider

sealed interface SignInUiState {

    object Loading : SignInUiState

    object Success : SignInUiState

    data class Failure(val exception: Exception) : SignInUiState

    data class NotSignedUpYet(val accessToken: String, val oAuthProvider: OAuthProvider) :
        SignInUiState
}
