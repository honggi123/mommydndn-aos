package com.mommydndn.app.ui.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.utils.bottomBorder

@Composable
fun SearchUnderHeader(
    headerText: String = "",
    searchAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 16.dp)
                .bottomBorder(1.dp, Grey100)
        ) {
            // 버튼 컴포넌트로 변경 필요
            Button(modifier = Modifier
                .width(342.dp)
                .height(48.dp),
                onClick = { searchAction() }
            ) {
                Text(text = "현재 위치로 찾기", style = TextStyle(color = Grey700))
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
                    text = headerText,
                    style = MaterialTheme.typography.paragraph300.copy(
                        fontWeight = FontWeight.Bold,
                        color = Grey700,
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
fun previewSearchUnderHeader() {
    MommydndnaosTheme {
        SearchUnderHeader(headerText = "근처 동네", searchAction = {})
    }
}