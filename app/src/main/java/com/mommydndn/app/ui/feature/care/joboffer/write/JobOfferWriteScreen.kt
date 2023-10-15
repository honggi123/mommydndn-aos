package com.mommydndn.app.ui.feature.care.joboffer.write

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.box.PostTextFieldBox
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.SubtextBoxSize
import com.mommydndn.app.ui.components.button.CtaButton
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.TextInpuField
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun JobOfferWriteScreen() {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_x_circle),
                contentDescription = "",
                modifier = Modifier
                    .size(size = 36.dp)
            )
        }, centerContent = {
            Text(
                text = "구인글 쓰기",
                style = MaterialTheme.typography.paragraph300.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }, rightContent = {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(White),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "불러오기",
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey500,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        })

        LazyColumn() {
            item {
                PostTextFieldBox(title = "", content = "")
            }
            item {
                SubtextBox(titleText = "필요한 돌봄", subtitleText = "(필수)", size = SubtextBoxSize.S)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(titleText = "일하는 시간", subtitleText = "(필수)", size = SubtextBoxSize.S)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(titleText = "일하는 장소", subtitleText = "(필수)", size = SubtextBoxSize.S)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(titleText = "임금", subtitleText = "(필수)", size = SubtextBoxSize.S)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(titleText = "사진", subtitleText = "(선택)", size = SubtextBoxSize.S)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Grey50)
                        .padding(20.dp)
                )
            }
            item {
                SubtextBox(titleText = "기타조건", subtitleText = "(선택)", size = SubtextBoxSize.S)
            }

            item {
                CtaButton(text = "다음으로") {}
            }
        }

    }
}

@Preview
@Composable
fun previewJobOfferWriteScreen() {
    MommydndnaosTheme {
        JobOfferWriteScreen()
    }
}


