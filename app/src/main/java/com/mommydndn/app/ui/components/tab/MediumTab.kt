package com.mommydndn.app.ui.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph500

@Composable
fun MediumTab(
    tabNames: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        backgroundColor = White,
        contentColor = Grey500,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(3.dp)
                    .padding(horizontal = 20.dp)
                    .background(color = Grey700)
            )
        }
    ) {
        tabNames.forEachIndexed { index, tabName ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                },
                text = {
                    Text(
                        text = tabName,
                        style = MaterialTheme.typography.paragraph500.merge(
                            fontWeight = FontWeight.Medium
                        ),
                    )
                },
            )
        }
    }
}
