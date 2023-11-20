package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption200
@Composable
fun SmallProfileInfoStack(
    modifier: Modifier = Modifier,
    dateText: String = "",
    certificationList: List<String> = emptyList(),
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 342.dp)
            .background(color = White)
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
                    text = "가입일 " + dateText,
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
}