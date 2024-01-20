package com.mommydndn.app.ui.care.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.ui.care.list.components.FilterChip
import com.mommydndn.app.ui.care.list.filter.CareFilter
import com.mommydndn.app.ui.care.list.filter.CareOrderBy
import com.mommydndn.app.ui.care.list.filter.CareTypeFilter
import com.mommydndn.app.ui.care.list.filter.DayOfWeekFilter
import com.mommydndn.app.ui.care.list.filter.NeighborhoodFilter
import com.mommydndn.app.ui.care.list.filter.WorkHoursFilter
import com.mommydndn.app.ui.care.list.filter.WorkPeriodFilter
import com.mommydndn.app.ui.care.list.filter.displayName
import com.mommydndn.app.ui.care.post.components.TopAppBarHeight
import com.mommydndn.app.ui.components.tab.MediumTab
import com.mommydndn.app.ui.theme.Grey300
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.heading600

@Composable
internal fun Care(
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CareViewModel = hiltViewModel(),
) {
    /*
    CareContent(
        neighborhood = uiState.neighborhood,
        onNeighborhoodClick = onNeighborhoodClick,
        onSearchClick = onSearchClick,
        selectedTabIndex = selectedTabIndex.intValue,
        onTabSelected = {
            selectedTabIndex.intValue = it
        },
        orderBy = uiState.orderBy,
        onOrderClick = {},
        filters = uiState.filters,
        onFilterClick = {
            // TODO
            selectedFilter.value = it

            coroutineScope.launch {
                sheetState.show()
            }
        },
        jobOpeningListItems = uiState.jobs,
        selectedFilter = selectedFilter.value,
        sheetState = sheetState,
        onSheetCloseClick = {
            coroutineScope.launch {
                sheetState.hide()
            }
        },
        onFilterUpdated = viewModel::setFilter,
        modifier = modifier,
    )
     */
}

data class NeighborhoodUiModel(
    val id: Long,
    val name: String,
    val address: String,
    val nearbyDistance: NearbyNeighborhoodDistance,
    val nearbyNeighborhoods: Map<NearbyNeighborhoodDistance, List<Neighborhood>>,
)

@Composable
private fun CareContent(
    neighborhood: NeighborhoodUiModel,
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    selectedTabIndex: Int,
    onTabSelected: (index: Int) -> Unit,
    orderBy: CareOrderBy,
    onOrderClick: () -> Unit,
    filters: List<CareFilter>,
    onFilterClick: (CareFilter) -> Unit,
    jobOpeningListItems: List<CareJobUiModel>,
    selectedFilter: CareFilter?,
    sheetState: ModalBottomSheetState,
    onSheetCloseClick: () -> Unit,
    onFilterUpdated: (CareFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedFilter != null) { // TODO
                CareFilterModalBottomSheet(
                    selectedFilter = selectedFilter,
                    onCloseClick = onSheetCloseClick,
                    onFilterUpdated = onFilterUpdated,
                )
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        sheetContentColor = Color.Transparent,
        scrimColor = GreyOpacity400,
        modifier = modifier,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                CareTopAppBar(
                    neighborhoodName = neighborhood.name,
                    onNeighborhoodClick = onNeighborhoodClick,
                    onSearchClick = onSearchClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TopAppBarHeight)
                        .padding(horizontal = 20.dp),
                )

                CareTabRow(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = onTabSelected,
                    modifier = Modifier.fillMaxWidth(),
                )

                CareOrderAndFilters(
                    orderBy = orderBy,
                    onOrderByClick = onOrderClick,
                    filters = filters,
                    onFilterClick = onFilterClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                )

                CareJobList(
                    jobs = jobOpeningListItems,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun CareTopAppBar(
    neighborhoodName: String, // todo: type
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onNeighborhoodClick),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = neighborhoodName,
                color = Grey700,
                style = MaterialTheme.typography.heading600.merge(
                    fontWeight = FontWeight.Bold,
                )
            )

            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_down),
                contentDescription = "CareTopAppBar_Neighborhood_ArrowDown",
                modifier = Modifier.size(36.dp),
                tint = Grey600,
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.icon_search),
            contentDescription = "CareTopAppBar_Search",
            modifier = Modifier
                .size(32.dp)
                .clickable(onClick = onSearchClick),
            tint = Grey300,
        )
    }
}

@Composable
private fun CareTabRow(
    selectedTabIndex: Int,
    onTabSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    tabNames: List<String> = listOf(
        stringResource(R.string.job_opening),
        stringResource(R.string.job_hunting),
        stringResource(R.string.authenticated_agency)
    )
) {
    MediumTab(
        tabNames = tabNames,
        selectedTabIndex = selectedTabIndex,
        onTabSelected = onTabSelected,
        modifier = modifier
    )
}

@Composable
private fun CareOrderAndFilters(
    orderBy: CareOrderBy,
    onOrderByClick: () -> Unit,
    filters: List<CareFilter>,
    onFilterClick: (CareFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))

        FilterChip(
            selected = true,
            text = orderBy.displayName,
            onClick = onOrderByClick,
        )

        filters.sortedByDescending { it.selected }.forEach { filter ->
            FilterChip(
                selected = filter.selected,
                text = filter.displayName(),
                onClick = { onFilterClick(filter) },
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
private fun CareFilterModalBottomSheet(
    selectedFilter: CareFilter,
    onCloseClick: () -> Unit,
    onFilterUpdated: (CareFilter) -> Unit,
) {
    when (selectedFilter) {
        is NeighborhoodFilter -> {
            TODO()
        }
        is CareTypeFilter -> {
            TODO()
        }
        is DayOfWeekFilter -> {
            TODO()
        }
        is WorkHoursFilter -> {
            TODO()
        }
        is WorkPeriodFilter -> {
            TODO()
        }
    }
}

@Preview
@Composable
private fun CareScreenPreview() {
    val fakeNeighborhood = Neighborhood(
        id = 5264,
        name = "Miranda Russo",
        address = "option"
    )

    val neighborhoodDummy = NeighborhoodUiModel(
        id = 0,
        name = "서초동",
        address = "서울 서초구 서초중앙로 15",
        nearbyDistance = NearbyNeighborhoodDistance.VeryDistant,
        nearbyNeighborhoods = mapOf(
            NearbyNeighborhoodDistance.Immediate to emptyList(),
            NearbyNeighborhoodDistance.Nearby to buildList {
                repeat(5) {
                    add(fakeNeighborhood)
                }
            },
            NearbyNeighborhoodDistance.Distant to buildList {
                repeat(17) {
                    add(fakeNeighborhood)
                }
            },
            NearbyNeighborhoodDistance.VeryDistant to buildList {
                repeat(24) {
                    add(fakeNeighborhood)
                }
            }
        ),
    )

    CareContent(
        neighborhood = neighborhoodDummy,
        onNeighborhoodClick = {},
        onSearchClick = {},
        selectedTabIndex = 0,
        onTabSelected = {},
        orderBy = CareOrderBy.LATEST,
        onOrderClick = {},
        filters = listOf(
            NeighborhoodFilter(neighborhoodDummy),
            CareTypeFilter(),
            DayOfWeekFilter(),
            WorkHoursFilter(),
            WorkPeriodFilter()
        ),
        onFilterClick = {},
        jobOpeningListItems = jobListDummy,
        selectedFilter = null,
        sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
        onSheetCloseClick = {},
        onFilterUpdated = {},
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    )
}