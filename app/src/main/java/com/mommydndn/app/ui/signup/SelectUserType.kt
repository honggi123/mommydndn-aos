package com.mommydndn.app.ui.signup

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.user.UserType
import com.mommydndn.app.ui.care.components.post.TopAppBarHeight
import com.mommydndn.app.ui.signup.components.AboutAgencyUser
import com.mommydndn.app.ui.signup.components.UserTypeButton
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.ui.theme.paragraph300
import kotlinx.coroutines.launch

@Composable
fun SelectUserTypeScreen(
    onBackClick: () -> Unit,
    userType: UserType?,
    onUserTypeClick: (UserType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.TopStart),
        ) {
            TopAppBar(onBackClick = onBackClick)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 32.dp, top = 16.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.welcome_message),
                    color = Grey500,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )

                Text(
                    text = stringResource(R.string.ask_user_type),
                    color = Grey700,
                    style = MaterialTheme.typography.heading800.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            UserTypeButton(
                onClick = {
                    onUserTypeClick(UserType.COMPANY)
                },
                iconDrawableId = R.drawable.icon_building,
                text = stringResource(id = R.string.agency_user),
                selected = userType == UserType.COMPANY,
                modifier = Modifier.weight(1F),
            )

            UserTypeButton(
                onClick = {
                    onUserTypeClick(UserType.INDIVIDUAL)
                },
                iconDrawableId = R.drawable.icon_person,
                text = stringResource(id = R.string.individual_user),
                selected = userType == UserType.INDIVIDUAL,
                modifier = Modifier.weight(1F),
            )
        }

        val coroutineScope = rememberCoroutineScope()

        val y = remember {
            Animatable(0F)
        }

        if (y.value < 512F) {
            AboutAgencyUser(
                onCloseClick = {
                    coroutineScope.launch {
                        y.animateTo(
                            targetValue = 512F,
                            animationSpec = tween(durationMillis = 500)
                        )
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .offset {
                        IntOffset(0, y.value.toInt())
                    }
            )
        }
    }
}

@Composable
private fun TopAppBar(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(TopAppBarHeight),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_left),
            contentDescription = "UserTypeSelection_TopAppBar_ArrowLeft",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
                .size(36.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onBackClick,
                ),
            tint = Grey400
        )
    }
}

@Preview
@Composable
private fun SelectUserTypeScreenPreview() {
    var userType by remember {
        mutableStateOf<UserType?>(null)
    }

    SelectUserTypeScreen(
        onBackClick = {},
        userType = userType,
        onUserTypeClick = {
            userType = it
        },
        modifier = Modifier
            .background(White)
            .fillMaxSize(),
    )
}