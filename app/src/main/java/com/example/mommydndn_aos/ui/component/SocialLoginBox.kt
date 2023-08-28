package com.example.mommydndn_aos.ui.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mommydndn_aos.R
import com.example.mommydndn_aos.ui.theme.Grey700
import com.example.mommydndn_aos.ui.theme.MommydndnaosTheme
import com.example.mommydndn_aos.ui.theme.Paddings
import com.example.mommydndn_aos.ui.theme.paragraph300


@Composable
fun SocialLoginBox() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .width(390.dp)
                .height(310.dp)
                .padding(top = 96.dp, bottom = 96.dp),
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
                    onClick = {},
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_google),
                        contentDescription = "",
                        modifier = Modifier
                            .size(72.dp)
                    )
                }
                Spacer(modifier = Modifier.width(Paddings.xlarge))
                IconButton(
                    onClick = {},
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
                    onClick = {},
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_naver),
                        contentDescription = "",
                        modifier = Modifier
                            .size(72.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SocialLoginBoxPreview() {
    MommydndnaosTheme {
        SocialLoginBox()
    }
}
