package com.mommydndn.app.ui.components.box

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Paddings
import com.mommydndn.app.ui.theme.paragraph300


@Composable
fun SocialLoginBox(
    modifier: Modifier = Modifier,
    onClickKakao: () -> Unit,
    onClickGoogle: () -> Unit,
    onClickNaver: () -> Unit
) {
    Column(
        modifier = modifier
            .width(390.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = AnnotatedString(stringResource(id = R.string.social_login_label)),
            style = MaterialTheme.typography.paragraph300
                .copy(
                    color = Grey700,
                    fontWeight = FontWeight.Medium
                ),
        )
        Spacer(modifier = Modifier.height(Paddings.xlarge))

        Row {
            IconButton(
                onClick = { onClickNaver() },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_naver),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                )
            }
            Spacer(modifier = Modifier.width(Paddings.xlarge))

            IconButton(
                onClick = { onClickKakao() },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_kakao),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                )
            }
            Spacer(modifier = Modifier.width(Paddings.xlarge))

            IconButton(
                onClick = { onClickGoogle() },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_google),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                )
            }
        }
    }
}

