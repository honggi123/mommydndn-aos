package com.mommydndn.app.deprecated.components.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300

@Composable
fun InfoBox(
    modifier: Modifier = Modifier,
    salaryText: String = "",
    dateText: String = "",
    timeText: String = ""
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Grey50, shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_money_circle),
                    contentDescription = "",
                    tint = Grey400
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = salaryText,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Grey700
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_calender),
                    contentDescription = "",
                    tint = Grey400
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = dateText,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = Grey600
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.ic_circle),
                        contentDescription = "",
                        tint = Grey400
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = timeText,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = Grey600
                )
            }
        }
    }
}

@Preview
@Composable
fun previewInfoBox() {
    InfoBox(salaryText = "10,000", dateText = "8월 12일 ~ 8월 13일", timeText = "17:00 ~ 18:00")
}
