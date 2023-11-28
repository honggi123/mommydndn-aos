package com.mommydndn.app.ui.features.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.ui.components.box.MaintextBox
import com.mommydndn.app.ui.components.button.SquareButton
import com.mommydndn.app.R
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.navigation.LocationSearchNav
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.util.NavigationUtils

@Composable
internal fun UserTypeRoute(
    signUpInfo: SignUpInfo,
    navHostController: NavHostController,
    onExploreClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.setSignUpInfo(signUpInfo)
    }

    val onUserTypeClick: (UserType) -> Unit = { userType ->
        when (userType) {
            UserType.COMPANY -> {
                viewModel.setUserType(UserType.COMPANY)
            }

            UserType.INDIVIDUAL -> {
                viewModel.setUserType(UserType.INDIVIDUAL)
            }
        }
    }


    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        SignUpUiState.Loading -> UserTypeScreen(
            onExploreClick = onExploreClick,
            onUserTypeClick = onUserTypeClick
        )
        is SignUpUiState.UserTypeSelected -> {
            NavigationUtils.navigate(
                navHostController,
                LocationSearchNav.route
            )
        }
        else -> {
            // TODO
        }
    }


}

@Composable
fun UserTypeScreen(
    onExploreClick: () -> Unit,
    onUserTypeClick: (UserType) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        UserTypeTopAppBar(onExploreClick = onExploreClick)

        MaintextBox(
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
    onExploreClick: () -> Unit
) {
    Header(leftContent = {
        IconButton(onClick = onExploreClick) {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = "icon_arrow_left",
                tint = Grey400
            )
        }
    })
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
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SquareButton(
                imageResourceId = R.drawable.icon_building,
                text = stringResource(R.string.company_user),
                onClick = { onUserTypeClick(UserType.COMPANY) }
            )
            Spacer(modifier = Modifier.padding(16.dp))
            SquareButton(
                imageResourceId = R.drawable.icon_person,
                text = stringResource(R.string.individual_user),
                onClick = { onUserTypeClick(UserType.INDIVIDUAL) }
            )
        }
    }
}

