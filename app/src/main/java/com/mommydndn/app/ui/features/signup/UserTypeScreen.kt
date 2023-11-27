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
    onExploreClick: () -> Unit,
    onSignInSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.setSignUpInfo(signUpInfo)
    }

}

@Composable
fun UserTypeScreen(
    navHostController: NavHostController,
    viewModel: SignUpViewModel
) {

    Column(modifier = Modifier.fillMaxSize()) {
        UserTypeTopAppBar(onExploreClick = { navHostController.popBackStack() })

        MaintextBox(
            captionText = stringResource(R.string.welcome_message),
            titleText = stringResource(R.string.ask_user_type)
        )

        Box(
            modifier = Modifier
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
                    imageResourceId = R.drawable.building_graphic,
                    text = stringResource(R.string.company_user),
                    onClick = {
                        viewModel.setUserType(UserType.COMPANY)
                        NavigationUtils.navigate(
                            navHostController,
                            LocationSearchNav.route
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                SquareButton(
                    imageResourceId = R.drawable.person_graphic,
                    text = stringResource(R.string.individual_user),
                    onClick = {
                        viewModel.setUserType(UserType.INDIVIDUAL)
                        NavigationUtils.navigate(
                            navHostController,
                            LocationSearchNav.route
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun UserTypeTopAppBar(
    onExploreClick: () -> Unit
) {
    Header(leftContent = {
        IconButton(onClick = onExploreClick) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "back",
                tint = Grey400
            )
        }
    })
}


