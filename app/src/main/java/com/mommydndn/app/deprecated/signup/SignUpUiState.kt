package com.mommydndn.app.deprecated.signup

import com.mommydndn.app.domain.model.tos.TermsOfService

sealed interface SignUpUiState {

    sealed class UserTypeSelect : SignUpUiState {

        object Loading : UserTypeSelect()
        object Success : UserTypeSelect()
        data class Failure(val exception: Exception) : UserTypeSelect()

    }

    sealed class LocationSearch : SignUpUiState {

        object Loading : LocationSearch()

        data class Success(
            val tosList: List<TermsOfService> = emptyList()
        ) : LocationSearch()

        data class Failure(val exception: Exception) : LocationSearch()

        object SignUpSuccess : LocationSearch()

    }
}