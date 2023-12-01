package com.mommydndn.app.ui.features.care

import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.care.WorkPeriod
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.user.Neighborhood
import com.mommydndn.app.ui.components.chip.ChipWithBottomArrow
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.modal.layout.BaseModalBottomSheetLayout
import com.mommydndn.app.ui.components.tab.MediumTab
import com.mommydndn.app.ui.features.care.modal.NeighborhoodsFilterModalBottomSheet
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.heading800
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import java.util.Locale

@Composable
internal fun CareRoute(
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CareViewModel = hiltViewModel(),
) {
    val selectedTabIndex = remember { mutableIntStateOf(0) }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val selectedFilter = remember { mutableStateOf<CareFilter?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = spring(dampingRatio = 0.85f, stiffness = 100f),
        skipHalfExpanded = true,
    )

    when (val uiStateValue = uiState.value) {
        CareUiState.Loading -> {

        }
        is CareUiState.Success -> CareScreen(
            neighborhood = uiStateValue.neighborhood,
            onNeighborhoodClick = onNeighborhoodClick,
            onSearchClick = onSearchClick,
            selectedTabIndex = selectedTabIndex.intValue,
            onTabSelected = {
                selectedTabIndex.intValue = it
            },
            order = uiStateValue.order,
            onOrderClick = {},
            filters = uiStateValue.filters,
            onFilterClick = {
                // todo: recompose twice?
                selectedFilter.value = it

                coroutineScope.launch {
                    sheetState.show()
                }
            },
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
        is CareUiState.Failure -> {

        }
    }
}

@Composable
private fun CareScreen(
    neighborhood: Neighborhood,
    onNeighborhoodClick: () -> Unit,
    onSearchClick: () -> Unit,
    selectedTabIndex: Int,
    onTabSelected: (index: Int) -> Unit,
    order: CareOrderBy,
    onOrderClick: () -> Unit,
    filters: List<CareFilter>,
    onFilterClick: (CareFilter) -> Unit,
    selectedFilter: CareFilter?,
    sheetState: ModalBottomSheetState,
    onSheetCloseClick: () -> Unit, // todo: rename?
    onFilterUpdated: (CareFilter) -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseModalBottomSheetLayout(
        sheetContent = {
            if (selectedFilter != null) {
                CareFilterModalBottomSheet(
                    selectedFilter = selectedFilter,
                    onCloseClick = onSheetCloseClick,
                    onFilterUpdated = onFilterUpdated,
                )
            }
        },
        sheetState = sheetState,
        modifier = modifier,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                CareTopAppBar(
                    neighborhoodName = neighborhood.name,
                    onNeighborhoodClick = onNeighborhoodClick,
                    onSearchClick = onSearchClick,
                    modifier = Modifier.fillMaxWidth(),
                )

                CareTabRow(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = onTabSelected,
                    modifier = Modifier.fillMaxWidth(),
                )

                CareOrderAndFilters(
                    order = order,
                    onOrderClick = onOrderClick,
                    filters = filters.toList(),
                    onFilterClick = onFilterClick,
                    modifier = Modifier.fillMaxWidth()
                )

                // todo: tab_page_content
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
    Header(
        modifier = modifier,
        leftContent = {
            Button(
                onClick = onNeighborhoodClick,
                content = {
                    Text(
                        text = neighborhoodName,
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
    // todo: rename?
    order: CareOrderBy,
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

private val CareFilter.selected: Boolean get() = state == CareFilter.State.SELECTED

@Composable
private fun CareFilter.displayName(): String {
    return when (this) {
        is NeighborhoodsFilter -> {
            val nearbyNeighborhoodsCount = when (nearbyNeighborhoodDistance) {
                NearbyNeighborhoodDistance.IMMEDIATE -> 0
                NearbyNeighborhoodDistance.NEARBY -> neighborhood.nearbyNeighborhoods.size
                NearbyNeighborhoodDistance.DISTANT -> neighborhood.distantNeighborhoods.size
                NearbyNeighborhoodDistance.VERY_DISTANT -> neighborhood.veryDistantNeighborhoods.size
            }

            if (nearbyNeighborhoodsCount == 0) {
                neighborhood.name
            } else {
                stringResource(R.string.neighborhoods, neighborhood.name, nearbyNeighborhoodsCount)
            }
        }
        is CareTypesFilter -> if (careTypes.isNullOrEmpty()) {
            stringResource(R.string.care_type)
        } else {
            careTypes.sorted().let { careTypes ->
                val postfix = if (careTypes.size > 2) {
                    " 외 ${careTypes.size - 2}"
                } else {
                    ""
                }

                careTypes.drop(2).joinToString(
                    postfix = postfix,
                    transform = { it.name }
                )
            }
        }
        is PayFilter -> if (minimum != null && maximum != null) {
            stringResource(
                R.string.hourly_pay,
                String.format("%.1F", minimum / 10_000.0),
                String.format("%.1F", maximum / 10_000.0)
            )
        } else {
            stringResource(R.string.pay)
        }
        is WorkingDaysAndHoursFilter -> if (!selected) {
            stringResource(R.string.time)
        } else {
            val daysOfWeekString = daysOfWeek.orEmpty().sorted().let { daysOfWeek ->
                val postfix: String = if (daysOfWeek.size >= 4) {
                    " 외 ${daysOfWeek.size - 3}"
                } else {
                    ""
                }
                daysOfWeek.drop(3).joinToString(
                    separator = "",
                    postfix = postfix,
                    transform = {
                        it.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.KOREAN)
                    }
                )
            }

            val hoursString = if (start != null && end != null) {
                "${start.hour}시-${end.hour}시"
            } else {
                ""
            }

            "$daysOfWeekString, $hoursString"
        }
        is WorkPeriodFilter -> when (workPeriod) {
            WorkPeriod.ONE_TIME -> stringResource(R.string.one_time)
            WorkPeriod.REGULAR -> stringResource(R.string.regular)
            else -> stringResource(R.string.one_time_slash_regular)
        }
    }
}

@Composable
private fun CareFilterModalBottomSheet(
    selectedFilter: CareFilter,
    onCloseClick: () -> Unit,
    onFilterUpdated: (CareFilter) -> Unit,
) {
    when (selectedFilter) {
        is NeighborhoodsFilter -> {
            NeighborhoodsFilterModalBottomSheet(
                neighborhood = selectedFilter.neighborhood,
                nearbyNeighborhoodDistance = selectedFilter.nearbyNeighborhoodDistance,
                onCloseClick = onCloseClick,
                onUpdateClick = {
                    onFilterUpdated(
                        selectedFilter.copy(nearbyNeighborhoodDistance = it)
                    )
                },
                modifier = Modifier,
            )
        }
        is CareTypesFilter -> TODO()
        is PayFilter -> TODO()
        is WorkingDaysAndHoursFilter -> TODO()
        is WorkPeriodFilter -> TODO()
    }
}