package com.mommydndn.app.ui.care.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mommydndn.app.domain.model.CareWorkerOtherCondition
import com.mommydndn.app.domain.model.OtherCondition
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
import java.time.LocalDate

@Composable
internal fun CareDetailsAbout(
    name: String,
    verifications: List<String>,
    registeredAt: LocalDate,
    pay: String,
    otherConditions: List<OtherCondition>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CareDetailsSectionTitle(title = stringResource(R.string.about, name))

        if (verifications.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                verifications.forEach {
                    DetailsVerification(it)
                }
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            DetailsRegisteredAt(registeredAt = registeredAt)

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

        if (otherConditions.isNotEmpty()) {
            CareDetailsOtherConditionTags(otherConditions)
        }
    }
}

@Composable
internal fun DetailsRegisteredAt(
    registeredAt: LocalDate
) {
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
            text = with(registeredAt) {
                stringResource(R.string.registered_date, year, monthValue, dayOfMonth)
            },
            color = Grey700,
            style = MaterialTheme.typography.caption200.merge(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
internal fun DetailsVerification(verification: String) {
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

@Preview
@Composable
private fun AbountPreview() {
    CareDetailsAbout(
        name = "세아쌤",
        verifications = listOf(
            "서초동 엄마 인증 완료",
            "선생님 인증 완료",
            "보육교사 인증 완료",
            "서울대학교 인증 완료"
        ),
        registeredAt = LocalDate.now(),
        pay = "희망시급 12,000원 ~ 14,000원",
        otherConditions = listOf(
            CareWorkerOtherCondition.CCTV,
            CareWorkerOtherCondition.NoReligion,
            CareWorkerOtherCondition.NonSmoker,
        ),
        modifier = Modifier
            .background(White)
            .padding(horizontal = 24.dp, vertical = 28.dp)
    )
}