package com.example.mommydndn_aos.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mommydndn_aos.ui.component.Header
import com.mommydndn.app.ui.component.SocialLoginBox
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Paddings
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun LoginScreen() {
    Scaffold(
        topBar = {
            Header(
                rightContent = {
                    ClickableText(
                        modifier = Modifier.padding(end = 20.dp),
                        text = AnnotatedString("먼저 둘러보기"),
                        style = MaterialTheme.typography.paragraph300.copy(
                            color = Grey500,
                            fontWeight = FontWeight.Medium
                        ),
                        onClick = {}
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .width(125.dp)
                            .height(121.dp),
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(Paddings.extra))
                    Text(
                        text = "우리 동네 엄마 찾기",
                        style = MaterialTheme.typography.heading800.copy(
                            color = Grey500,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Spacer(modifier = Modifier.height(Paddings.xlarge))
                    Text(
                        text = "내 아이를 맡길 수 있는\n따뜻한 동네 엄마를 찾아보세요",
                        style = MaterialTheme.typography.paragraph300.copy(
                            color = Grey500,
                            fontWeight = FontWeight.Medium
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            SocialLoginBox(
                onClickGoogle = { loginGoogle() },
                onClickKakao = { loginKakao() },
                onClickNaver = { loginNaver() }
            )
        }
    }
}

@Preview
@Composable
fun previewScreen() {
    LoginScreen()
}

private fun loginKakao() {}
private fun loginGoogle() {}
private fun loginNaver() {}