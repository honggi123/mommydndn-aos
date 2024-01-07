package com.mommydndn.app.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Typography
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
internal fun HomeFooter(
    onInquiryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(Grey50)
                .padding(horizontal = 32.dp, vertical = 64.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(3.5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_logo_grey),
                    contentDescription = "HomeFooter_LogoGrey",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified,
                )

                Icon(
                    painter = painterResource(id = R.drawable.icon_logo_text),
                    contentDescription = "HomeFooter_LogoText",
                    modifier = Modifier,
                    tint = Grey400,
                )
            }

            Column {
                Text(
                    text = stringResource(R.string.mommydndn_corporation),
                    color = Grey500,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Bold,
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.ceo),
                        color = Grey400,
                        style = Typography.caption200,
                    )

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(11.dp)
                            .background(Grey400),
                    )

                    Text(
                        text = stringResource(R.string.registration_number),
                        color = Grey400,
                        style = Typography.caption200,
                    )
                }

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.mommydndn_address),
                        color = Grey400,
                        style = Typography.caption200
                    )

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(11.dp)
                            .background(Grey400),
                    )

                    Text(
                        text = stringResource(R.string.mommydndn_email),
                        color = Grey400,
                        style = Typography.caption200
                    )
                }

                Text(
                    text = "직업정보제공사업 서울동부 2021-20호",
                    color = Grey400,
                    style = Typography.caption200
                )

                Text(
                    text = "통신판매신고번호 2018-서울송파-0033",
                    color = Grey400,
                    style = Typography.caption200
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "이용약관",
                    color = Grey500,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Bold,
                    )
                )

                Text(
                    text = "개인정보처리방침",
                    color = Grey500,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Bold,
                    )
                )

                Text(
                    text = "위치기반서비스",
                    color = Grey500,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Bold,
                    )
                )
            }

            Text(
                text = stringResource(R.string.one_on_one_inquiry),
                modifier = Modifier
                    .background(Grey100, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable(onClick = onInquiryClick),
                color = Grey600,
                style = Typography.paragraph300.merge(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Preview
@Composable
private fun HomeFooterPreview() {
    HomeFooter(
        onInquiryClick = {},
        modifier = Modifier,
    )
}