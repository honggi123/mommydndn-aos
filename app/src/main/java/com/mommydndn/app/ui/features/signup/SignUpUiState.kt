package com.mommydndn.app.ui.features.signup

import com.mommydndn.app.domain.model.location.SearchType
import com.mommydndn.app.domain.model.tos.TermsOfService

sealed interface SignUpUiState {

    sealed class UserTypeSelect : SignUpUiState {
        object Loading : UserTypeSelect()
        object LoadSuccess : UserTypeSelect()
        data class Failure(val exception: Exception) : UserTypeSelect()
    }

    sealed class LocationSearch : SignUpUiState {

        var searchType: SearchType = SearchType.MY_LOCATION
        var selectedLocationId: Int = 0
        var keyword: String = ""
        var TOSList: List<TermsOfService> = emptyList()

        object Loading : LocationSearch()

        object LoadSuccess : LocationSearch()

        data class Failure(val exception: Exception) : LocationSearch()

        object SignUpSuccess : LocationSearch()

    }
}