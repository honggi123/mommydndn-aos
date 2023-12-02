package com.mommydndn.app.ui.features.signup

import com.mommydndn.app.data.model.common.LocationSearchType
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.domain.model.TermsAndConditions.TermsAndConditionsItem

sealed interface SignUpUiState {

    val isLoading: Boolean
    val signUpInfo: SignUpInfo?
    val errorMessages: String

    data class UserTypeSelect(
        override val isLoading: Boolean,
        override val signUpInfo: SignUpInfo?,
        override val errorMessages: String
    ) : SignUpUiState

    data class LocationSearch(
        val locationSearchType: LocationSearchType,
        val keyword: String,
        val termsAndConditions: List<TermsAndConditionsItem>,
        val isSignUpSuccess: Boolean,
        override val isLoading: Boolean,
        override val signUpInfo: SignUpInfo?,
        override val errorMessages: String
    ) : SignUpUiState
}

enum class SignUpStep { USER_TYPE, SEARCH_LOCATION }
