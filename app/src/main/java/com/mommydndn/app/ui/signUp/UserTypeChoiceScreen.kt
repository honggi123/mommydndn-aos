package com.mommydndn.app.ui.signUp

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.ui.component.MaintextBox
import com.mommydndn.app.ui.component.button.SquareButton
import com.mommydndn.app.R
import com.mommydndn.app.data.model.OAuthType
import com.mommydndn.app.data.model.SignUpInfo
import com.mommydndn.app.data.model.UserType
import com.mommydndn.app.ui.TownCheckNav
import com.mommydndn.app.ui.component.Header
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.viewmodel.SignUpViewModel
import com.mommydndn.app.utils.NavigationUtils

@Composable
fun TypeChoiceScreen(
    signUpInfo: SignUpInfo?,
    navHostController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.updateSignUpInfo(signUpInfo)
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
                        viewModel.updateUserType(UserType.COMPANY)

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
                        viewModel.updateUserType(UserType.INDIVIDUAL)

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

