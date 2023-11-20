package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.chip.LabelChip
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MediumProfileInfoStack(
    modifier: Modifier = Modifier,
    dateText: String = "",
    hourSalaryText: String?,
    averageSalaryText: String? = "",
    certificationList: List<String> = emptyList(),
    infos: List<String>
) {
    Box(
        modifier = modifier
            .background(color = White)
    ) {
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
            ) {
                certificationList.forEach { name ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_certificate),
                            contentDescription = "Icon/certificate",
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )
                        Text(
                            text = name,
                            color = Grey700,
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_calender),
                        contentDescription = "Icon/calender-filled",
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )

                    Text(
                        text = "가입일 ${hourSalaryText}",
                        color = Grey700,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_money_circle),
                        contentDescription = "Icon/calender-filled",
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )

                    Text(
                        text = "희망시급 ${dateText}",
                        color = Grey700,
                        style = MaterialTheme.typography.caption200.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                if(!averageSalaryText.isNullOrEmpty()){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_money_circle),
                            contentDescription = "Icon/calender-filled",
                            modifier = Modifier
                                .requiredSize(size = 16.dp)
                        )

                        Text(
                            text = "평균월급 ${dateText}",
                            color = Grey700,
                            style = MaterialTheme.typography.caption200.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                }

            }

            Box {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    infos.forEach { chipText ->
                        LabelChip(text = chipText)
                    }
                }
            }

        }
    }
}