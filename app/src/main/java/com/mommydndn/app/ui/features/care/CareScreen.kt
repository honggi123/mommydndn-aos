package com.mommydndn.app.ui.features.care

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.ui.components.chip.ChipWithBottomArrow
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.tab.MediumTab
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.heading800

@Composable
internal fun CareRoute() {

}

@Composable
private fun CareScreen(
    neighborhood: String,
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    selectedTabIndex: Int,
    onTabSelected: (index: Int) -> Unit,
    order: CareOrder,
    onOrderClick: () -> Unit,
    filters: List<CareFilter>,
    onFilterClick: (CareFilter) -> Unit,
) {
    CareTopAppBar(
        neighborhood = neighborhood,
        onNeighborhoodClick = onNeighborhoodClick,
        onSearchClick = onSearchClick,
    )

    CareTabRow(
        selectedTabIndex = selectedTabIndex,
        onTabSelected = onTabSelected,
        modifier = Modifier.fillMaxWidth(),
    )

    CareOrderAndFilters(
        order = order,
        onOrderClick = onOrderClick,
        filters = filters,
        onFilterClick = onFilterClick
    )
}

@Composable
private fun CareTopAppBar(
    neighborhood: String, // todo: type
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Header(
        modifier = modifier,
        leftContent = {
            Button(
                onClick = onNeighborhoodClick,
                content = {
                    Text(
                        text = neighborhood,
                        style = MaterialTheme.typography.heading800.merge(
                            color = Grey700,
                            fontWeight = FontWeight.Bold,
                        )
                    )

                    Image(
                        painter = painterResource(id = R.drawable.icon_arrow_down),
                        contentDescription = "CareTopAppBar_Neighborhood_ArrowDown",
                        modifier = Modifier.size(36.dp)
                    )
                },
            )
        },
        rightContent = {
            Button(
                onClick = onSearchClick,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.icon_search),
                        contentDescription = "CareTopAppBar_Search",
                        modifier = Modifier.size(36.dp)
                    )
                },
            )
        }
    )
}

@Composable
private fun CareTabRow(
    selectedTabIndex: Int,
    onTabSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // todo
    val tabNames = listOf(
        "구인글",
        "시터님",
        "안심업체"
    )

    MediumTab(
        tabNames = tabNames,
        selectedTabIndex = selectedTabIndex,
        onTabSelected = onTabSelected,
        modifier = modifier
    )
}

@Composable
private fun CareOrderAndFilters(
    // todo: rename
    order: CareOrder,
    onOrderClick: () -> Unit,
    filters: List<CareFilter>,
    onFilterClick: (CareFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ChipWithBottomArrow(
                selected = true,
                text = order.name, // todo
                onClick = onOrderClick,
            )
        }

        // todo: order
        items(filters.sortedByDescending { it.selected }) { filter ->
            ChipWithBottomArrow(
                selected = filter.selected,
                text = filter.displayName(),
                onClick = { onFilterClick(filter) }
            )
        }
    }
}

@Composable
private fun CareFilterModalBottomSheet(
    selectedFilter: CareFilter,
    filters: CareFilters,
    onFiltersUpdated: (CareFilters) -> Unit,
) {
   when (selectedFilter) {
        is NeighborhoodsFilter -> {
            /*
            val update = filters.copy(
                neighborhoods = ..
            )
             */
        }

        is CareTypesFilter -> TODO()
        is PayFilter -> TODO()
        is DaysOfWeekFilter -> TODO()
        is WorkingHoursFilter -> TODO()
        is CarePeriodFilter -> TODO()
    }
}