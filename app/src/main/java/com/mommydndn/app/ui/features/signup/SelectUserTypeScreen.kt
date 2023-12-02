package com.mommydndn.app.ui.features.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mommydndn.app.R
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.ui.components.box.MaintextBox
import com.mommydndn.app.ui.components.button.SquareButton
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.theme.Grey400

@Composable
internal fun SelectUserTypeRoute(
    navigateToNextScreen: () -> Unit,
    navigateToPreviousScreen: () -> Unit,
    signUpInfo: SignUpInfo?,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.setSignUpInfo(signUpInfo)
    }

    val onUserTypeClick: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.COMPANY -> {
                viewModel.setUserType(UserType.COMPANY)
                navigateToNextScreen()
            }

            UserType.INDIVIDUAL -> {
                viewModel.setUserType(UserType.INDIVIDUAL)
                navigateToNextScreen()
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val screenState = uiState as? SignUpUiState.UserTypeSelect

    screenState?.let {
        SelectUserTypeScreen(
            modifier = Modifier.fillMaxSize(),
            navigateToPreviousScreen = navigateToPreviousScreen,
            onUserTypeClick = onUserTypeClick,
            uiState = screenState
        )
    }
}

@Composable
fun SelectUserTypeScreen(
    modifier: Modifier = Modifier,
    navigateToPreviousScreen: () -> Unit,
    onUserTypeClick: (UserType) -> Unit,
    uiState: SignUpUiState.UserTypeSelect
) {

    Column(modifier = modifier.fillMaxSize()) {
        UserTypeTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            navigateToPreviousScreen = navigateToPreviousScreen
        )

        MaintextBox(
            modifier = Modifier.fillMaxWidth(),
            captionText = stringResource(R.string.welcome_message),
            titleText = stringResource(R.string.ask_user_type)
        )

        UserTypeContent(
            modifier = Modifier.fillMaxWidth(),
            onUserTypeClick = onUserTypeClick
        )
    }
}

@Composable
fun UserTypeTopAppBar(
    modifier: Modifier = Modifier,
    navigateToPreviousScreen: () -> Unit,
) {
    Header(
        modifier = modifier.fillMaxWidth(),
        leftContent = {
            IconButton(onClick = { navigateToPreviousScreen() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_left),
                    contentDescription = "icon_arrow_left",
                    tint = Grey400
                )
            }
        }
    )
}

@Composable
fun UserTypeContent(
    modifier: Modifier = Modifier,
    onUserTypeClick: (UserType) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(368.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SquareButton(
                modifier = Modifier.weight(1f),
                imageResourceId = R.drawable.icon_building,
                text = stringResource(R.string.company_user),
                onClick = { onUserTypeClick(UserType.COMPANY) }
            )
            SquareButton(
                modifier = Modifier.weight(1f),
                imageResourceId = R.drawable.icon_person,
                text = stringResource(R.string.individual_user),
                onClick = { onUserTypeClick(UserType.INDIVIDUAL) }
            )
        }
    }
}
