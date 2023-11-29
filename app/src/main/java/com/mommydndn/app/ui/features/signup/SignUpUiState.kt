package com.mommydndn.app.ui.features.signup

import androidx.compose.runtime.key
import com.mommydndn.app.data.model.common.LocationSearchType
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.domain.model.user.UserType

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
        val terms: List<TermsItem>,
        val isSignUpSuccess: Boolean,
        override val isLoading: Boolean,
        override val signUpInfo: SignUpInfo?,
        override val errorMessages: String
    ) : SignUpUiState
}


enum class SignUpStep(val position: Int) { USER_TYPE(0), SEARCH_LOCATION(1) }