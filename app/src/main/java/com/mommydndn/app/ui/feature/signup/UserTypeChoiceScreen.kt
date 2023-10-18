package com.mommydndn.app.ui.feature.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.ui.components.box.MaintextBox
import com.mommydndn.app.ui.components.button.SquareButton
import com.mommydndn.app.R
import com.mommydndn.app.data.model.user.SignUpInfo
import com.mommydndn.app.data.model.user.UserType
import com.mommydndn.app.ui.navigation.TownCheckNav
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.utils.NavigationUtils

@Composable
fun UserTypeChoiceScreen(
    signUpInfo: SignUpInfo?,
    navHostController: NavHostController,
    viewModel: SignUpViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.setSignUpInfo(signUpInfo)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Header(leftContent = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "back",
                    tint = Grey400
                )
            }
        })
        MaintextBox(
            captionText = "마미든든에 어서오세요 :)",
            titleText = "어떤 회원이신가요?"
        )
        Box(
            modifier = Modifier
                .width(390.dp)
                .height(368.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SquareButton(
                    imageResourceId = R.drawable.building_graphic,
                    text = "업체 회원",
                    onClick = {
                        viewModel.setUserType(UserType.COMPANY)

                        NavigationUtils.navigate(
                            navHostController,
                            TownCheckNav.route
                        )
                    }
                )
                SquareButton(
                    imageResourceId = R.drawable.person_graphic,
                    text = "개인 회원",
                    onClick = {
                        viewModel.setUserType(UserType.INDIVIDUAL)

                        NavigationUtils.navigate(
                            navHostController,
                            TownCheckNav.route
                        )
                    }
                )
            }
        }
    }
}

