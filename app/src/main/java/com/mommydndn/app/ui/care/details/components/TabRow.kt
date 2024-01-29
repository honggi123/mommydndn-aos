package com.mommydndn.app.ui.care.details.components

import androidx.compose.runtime.Composable

@Composable
internal fun DetailsTabRow(
    selectedTabIndex: Int,
    tabs: List<String>,
    onTabSelected: (index: Int) -> Unit,
) {

}

//    tabNames.forEachIndexed { index, tabName ->
//        Text(
//            text = tabName,
//            modifier = Modifier.clickable {
//                currentIndex = index
//
//                onTabSelected(currentIndex)
//            },
//            color = if (index == currentIndex) {
//                Grey700
//            } else {
//                Grey500
//            },
//            onTextLayout = { textLayoutResult ->
//                tabWidths[index] = with(density) {
//                    textLayoutResult.size.width.toDp()
//                }
//            },
//            style = MaterialTheme.typography.paragraph300.merge(
//                fontWeight = FontWeight.Medium,
//            )
//        )
//    }