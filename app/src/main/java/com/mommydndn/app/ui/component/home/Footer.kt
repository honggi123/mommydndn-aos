package com.mommydndn.app.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@Composable
fun Footer(
    onInquiryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(390.dp)
            .background(White),

    ) {
        Column(
            modifier = Modifier.padding(
                start = 32.dp,
                end = 32.dp,
                top = 64.dp,
                bottom = 64.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Grey400),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.padding(3.54.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_text),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Grey400)
                )
            }

            Spacer(modifier = Modifier.padding(24.dp))
            Text(
                text = "(주)마미든든 주식회사",
                style = MaterialTheme.typography.caption200.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey500,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "대표 이종현",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey400,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Divider(
                    modifier = Modifier
                        .height(11.dp)
                        .width(1.dp),
                    color = Grey400,
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = "사업자등록번호 740-88-00896",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey400,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "서울시 송파구 오금로 306",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey400,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Divider(
                    modifier = Modifier
                        .height(11.dp)
                        .width(1.dp),
                    color = Grey400,
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = "cs@mommydndn.com",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Medium,
                        color = Grey400,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }

            Text(
                text = "직업정보제공사업 서울동부 2021-20호",
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey400,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )

            Text(
                text = "통신판매신고번호 2018-서울송파-0033",
                style = MaterialTheme.typography.caption100.copy(
                    fontWeight = FontWeight.Medium,
                    color = Grey400,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )


            Spacer(modifier = Modifier.padding(24.dp))
            Row {
                Text(
                    text = "이용약관",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey500,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "개인정보처리방침",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey500,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )

            }

            Spacer(modifier = Modifier.padding(24.dp))
            Button(onClick = { onInquiryClick() }) {
                Text(
                    text = "1:1문의하기",
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey500,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun previewCFooter() {
    MommydndnaosTheme {
        Footer(onInquiryClick = {})
    }
}
