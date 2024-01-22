package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200

@Composable
internal fun DetailsAbout(
    name: String,
    verifications: List<String>,
    registeredAt: String,
    pay: String,
    tags: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DetailsSectionTitle(title = stringResource(R.string.about, name))

        if (verifications.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                verifications.forEach {
                    Verification(it)
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_calendar),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Grey300,
                )

                Text(
                    text = registeredAt,
                    color = Grey700,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_currency_won),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Unspecified,
                )

                Text(
                    text = pay,
                    color = Grey700,
                    style = MaterialTheme.typography.caption200.merge(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        if (tags.isNotEmpty()) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                tags.forEach { TagChip(tag = it) }
            }
        }
    }
}

@Composable
private fun Verification(verification: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_certificate),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.Unspecified,
        )

        Text(
            text = verification,
            color = Grey700,
            style = MaterialTheme.typography.caption200.merge(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun TagChip(tag: String) {
    Text(
        text = tag,
        modifier = Modifier
            .background(Grey50, CircleShape)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        color = Grey600,
        style = MaterialTheme.typography.caption200
    )
}

@Preview
@Composable
private fun AbountPreview() {
    DetailsAbout(
        name = "세아쌤",
        verifications = listOf(
            "서초동 엄마 인증 완료",
            "선생님 인증 완료",
            "보육교사 인증 완료",
            "서울대학교 인증 완료"
        ),
        registeredAt = "가입일 2023년 1월 10일",
        pay = "희망시급 12,000원 ~ 14,000원",
        tags = listOf(
            "CCTV 안 불편해요", "반려동물도 괜찮아요", "입주 가능해요", "비흡연자에요"
        ),
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 28.dp)
    )
}