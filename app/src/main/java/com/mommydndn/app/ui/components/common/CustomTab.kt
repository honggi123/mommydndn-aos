package com.mommydndn.app.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.data.model.common.TabSize
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.MommydndnaosTheme
import com.mommydndn.app.ui.theme.White

@Composable
fun CustomTab(
    tabs: List<String>,
    onTabClick: (Int) -> Unit,
    size: TabSize
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    TabRow(
        modifier = Modifier.fillMaxWidth(),
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
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onTabClick(index)
                    selectedTabIndex = index
                },
                text = { Text(title) },
            )
        }
    }
}

@Preview
@Composable
fun previewCustomTab(){
    MommydndnaosTheme {
        CustomTab(
            size = TabSize.LARGE,
            onTabClick = {},
            tabs = listOf("1","2","3")
        )
    }
}