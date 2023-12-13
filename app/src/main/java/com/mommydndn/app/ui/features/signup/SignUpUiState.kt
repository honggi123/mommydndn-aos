package com.mommydndn.app.ui.features.signup

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.model.location.SearchType
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
            val selectedLocation: LocationInfo? = null,
            val keyword: String = "",
            val TOSList: List<TermsOfService> = emptyList()
        ) : LocationSearch()

        data class Failure(val exception: Exception) : LocationSearch()

        object SignUpSuccess : LocationSearch()

    }
}

inline fun SignUpUiState.LocationSearch.takeIfSuccess(
    transform: SignUpUiState.LocationSearch.Success.() -> SignUpUiState.LocationSearch.Success
): SignUpUiState.LocationSearch {
    return when (this) {
        is SignUpUiState.LocationSearch.Success -> {
            val updatedState = transform()
            if (updatedState != this) updatedState else this
        }

        else -> this
    }
}