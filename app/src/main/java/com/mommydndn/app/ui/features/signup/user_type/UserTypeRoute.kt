package com.mommydndn.app.ui.features.signup.user_type

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.ui.features.signup.SignUpUiState
import com.mommydndn.app.ui.features.signup.SignUpViewModel

@Composable
internal fun UserTypeRoute(
    onUserTypeSelect: () -> Unit,
    onBackButtonClick: () -> Unit,
    // signUpInfo: SignUpInfo?,
    viewModel: SignUpViewModel = viewModel(),
) {
    LaunchedEffect(Unit) {
        // viewModel.updateSignUpInfo(signUpInfo)
    }

    val onUserTypeClick: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.COMPANY -> {
                // viewModel.updateUserType(UserType.COMPANY)
                onUserTypeSelect()
            }

            UserType.INDIVIDUAL -> {
                // viewModel.updateUserType(UserType.INDIVIDUAL)
                onUserTypeSelect()
            }
        }
    }

    val uiState by viewModel.userTypeSelectUiState.collectAsStateWithLifecycle()

    when (val uiState = uiState) {
        is SignUpUiState.UserTypeSelect.Loading -> {
            // TODO
        }

        is SignUpUiState.UserTypeSelect.Success -> {
            SelectUserTypeScreen(
                modifier = Modifier.fillMaxSize(),
                onBackButtonClick = onBackButtonClick,
                onUserTypeClick = onUserTypeClick,
                uiState = uiState
            )
        }

        is SignUpUiState.UserTypeSelect.Failure -> {
            // TODO
        }
    }

}