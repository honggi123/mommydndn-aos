package com.mommydndn.app.ui.deprecated.signup.user_type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.ui.deprecated.components.box.MaintextBox
import com.mommydndn.app.ui.deprecated.components.common.Header
import com.mommydndn.app.ui.deprecated.signup.SignUpUiState
import com.mommydndn.app.ui.deprecated.signup.component.SquareButton
import com.mommydndn.app.ui.theme.Grey400

@Composable
fun SelectUserTypeScreen(
    onBackButtonClick: () -> Unit,
    onUserTypeClick: (UserType) -> Unit,
    uiState: SignUpUiState.UserTypeSelect,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize()) {
        UserTypeTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            onBackButtonClick = onBackButtonClick
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
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Header(
        modifier = modifier.fillMaxWidth(),
        leftContent = {
            IconButton(onClick = { onBackButtonClick() }) {
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
    onUserTypeClick: (UserType) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(390f / 368f),
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
