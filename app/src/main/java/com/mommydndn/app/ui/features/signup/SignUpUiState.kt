package com.mommydndn.app.ui.features.signup

import com.mommydndn.app.data.model.common.LocationSearchType
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.domain.model.tos.TermsOfService

sealed interface SignUpUiState {

    sealed class UserTypeSelect : SignUpUiState {
        object Loading : UserTypeSelect()
        object Success : UserTypeSelect()
        data class Failure(val exception: Exception) : UserTypeSelect()
    }

    sealed class LocationSearch : SignUpUiState {

        var locationSearchType: LocationSearchType = LocationSearchType.LOCATION
        var keyword: String = ""
        var TOSList: List<TermsOfService> = emptyList()
        object Loading : LocationSearch()

        object SignUpSuccess : LocationSearch()

        object Success : LocationSearch()

        data class Failure(val exception: Exception) : LocationSearch()
    }
}