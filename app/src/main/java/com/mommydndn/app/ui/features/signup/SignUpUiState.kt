package com.mommydndn.app.ui.features.signup


sealed interface SignUpUiState {

    object Loading : SignUpUiState

    object Success : SignUpUiState

    data class Failure(val exception: Exception) : SignUpUiState

    object UserTypeSelected : SignUpUiState
}