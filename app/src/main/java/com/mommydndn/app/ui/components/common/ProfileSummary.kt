package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mommydndn.app.R
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400

@Composable
fun ProfileSummary() {

}

@Composable
fun ProfileBar(
    modifier: Modifier = Modifier,
    profileUrl: String = ""
) {
    val profilePainter = rememberImagePainter(
        data = profileUrl,
        builder = {
            crossfade(true)
        }
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .requiredWidth(width = 342.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = profilePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(108.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "워킹맘",
                        color = Grey700,
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_certificate),
                        contentDescription = "Icon/certificate",
                        modifier = Modifier
                            .size(size = 16.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "반포동",
                        color = Grey500,
                        style = MaterialTheme.typography.caption100.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "5.0",
                color = Salmon600,
                style = MaterialTheme.typography.paragraph400.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_muscle),
                    contentDescription = "Icon/muscle",
                    modifier = Modifier
                        .requiredSize(size = 16.dp)
                )
                Text(
                    text = "든든력",
                    color = Grey600,
                    style = MaterialTheme.typography.caption100.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun ProfileInfoStack(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
        modifier = modifier
            .requiredWidth(width = 342.dp)
            .background(color = White)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
        ) {
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
                    text = "서초동 엄마 인증 완료",
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
                text = "가입일 2023년 1월 10일",
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

@Composable
fun ProfileDataBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier.width(342.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "매칭 ",
                    color = Grey600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = "5회",
                    color = Salmon600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "후기 ",
                    color = Grey600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = "2건",
                    color = Salmon600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "응답률 ",
                    color = Grey600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = "100%",
                    color = Salmon600,
                    style = MaterialTheme.typography.caption200.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }
        }
    }
}

@Preview
@Composable
fun previewProfileDataBox() {
    ProfileDataBox()
}