package com.mommydndn.app.ui.features.care

import android.annotation.SuppressLint
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.filter.FilterItemsType
import com.mommydndn.app.data.model.care.filter.FilterType
import com.mommydndn.app.ui.components.box.JobOfferSummaryBox
import com.mommydndn.app.ui.components.chip.ChipWithBottomArrow
import com.mommydndn.app.ui.components.list.EnterpriseListItem
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.list.SitterListItem
import com.mommydndn.app.ui.components.modal.care.CaringBottomModal
import com.mommydndn.app.ui.components.modal.care.DayBottomModal
import com.mommydndn.app.ui.components.modal.care.NeighborhoodScopeBottomModal
import com.mommydndn.app.ui.components.modal.care.PeriodBottomModal
import com.mommydndn.app.ui.components.modal.layout.BaseModalBottomSheetLayout
import com.mommydndn.app.ui.components.modal.care.SortingBottomModal
import com.mommydndn.app.ui.components.modal.care.TimeBottomModal
import com.mommydndn.app.ui.components.tab.MediumCustomTab
import com.mommydndn.app.ui.models.care.SummaryTabType
import com.mommydndn.app.ui.navigation.CompanyWriteNav
import com.mommydndn.app.ui.navigation.JobOfferWriteNav
import com.mommydndn.app.ui.navigation.JobSeekerWriteNav
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.util.NavigationUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CareScreen(
    navController: NavHostController,
    viewModel: CareViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val userInfo by viewModel.userInfo.collectAsState()

    val searchedJobOfferSummary = viewModel.searchedJobOfferSummary.collectAsLazyPagingItems()
    val searchedJobSeekerSummary = viewModel.searchedJobSeekerSummary.collectAsLazyPagingItems()
    val searchedCompanySummary = viewModel.searchedCompanySummary.collectAsLazyPagingItems()

    val selectedTab by viewModel.selectedTab.collectAsState()

    val filterItems by viewModel.filterItems.collectAsState()

    var selectedItem by remember { mutableStateOf<FilterType?>(null) }

    val scope = rememberCoroutineScope()

    val sheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
            animationSpec = spring(
                dampingRatio = 0.85f,
                stiffness = 100f
            )
        )

    BaseModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            DialogContent(
                selectedItem = selectedItem,
                closeAction = {
                    scope.launch { sheetState.hide() }
                },
                completeAction = {
                    scope.launch {
                        sheetState.hide()
                    }
                },
                viewModel = viewModel,
                scaffoldState = scaffoldState
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(leftContent = {
                    Text(
                        text = userInfo?.emd?.name ?: "",
                        style = MaterialTheme.typography.heading800.copy(
                            fontWeight = FontWeight.Bold,
                            color = Grey700
                        )
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "",
                        modifier = Modifier
                            .size(36.dp)
                    )
                }, rightContent = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "",
                        modifier = Modifier
                            .size(36.dp)
                    )
                }
                )

                MediumCustomTab(
                    onTabClick = { viewModel.updateTabPosition(it) },
                    tabs = listOf("구인글", "시터님", "안심업체"),
                    selectedTabIndex = selectedTab?.index ?: 0
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 12.dp, bottom = 12.dp, start = 24.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filterItems) { item ->
                        ChipWithBottomArrow(
                            selected = item.isSelected,
                            text = item.displayingName,
                            onClick = {
                                scope.launch { sheetState.show() }
                                selectedItem = item
                            })
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 28.dp)
                        .background(White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (selectedTab == SummaryTabType.JOB_OFFER) {
                        itemsIndexed(
                            items = searchedJobOfferSummary
                        ) { index, item ->
                            if (item != null) {
                                JobOfferSummaryBox(
                                    modifier = Modifier.fillMaxWidth(),
                                    item = item
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Divider(
                                color = Grey50,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.5.dp)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    } else if (selectedTab == SummaryTabType.JOB_SEEKER) {
                        itemsIndexed(
                            items = searchedJobSeekerSummary
                        ) { index, item ->
                            if (item != null) {
                                SitterListItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    profileUrl = item.profileUrl,
                                    ageAndGender = item.ageAndGender,
                                    caringTypeCodeList = item.caringTypeCodeList,
                                    isDndnAuthenticated = item.isDndnAuthenticated,
                                    nickname = item.nickname,
                                    neighborhood = item.neighborhood,
                                    responseRate = item.responseRate,
                                    matchingCount = item.matchingCount,
                                    reviewCount = item.reviewCount,
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    } else if (selectedTab == SummaryTabType.COMPANY) {
                        itemsIndexed(
                            items = searchedCompanySummary
                        ) { index, item ->
                            if (item != null) {
                                EnterpriseListItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    nickname = item.nickname,
                                    neighborhood = item.neighborhood,
                                    profileUrl = item.profileUrl,
                                    isDndnAuthenticated = item.isDndnAuthenticated,
                                    dndnScore = item.dndnScore,
                                    caringTypeCodeList = item.caringTypeCodeList,
                                    matchingCount = item.matchingCount,
                                    reviewCount = item.reviewCount,
                                    responseRate = item.responseRate,
                                    isLiked = item.isLiked,
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }

                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 80.dp, end = 16.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        navigateToWriteScreen(navController, selectedTab)
                    },
                    modifier = Modifier
                        .size(72.dp),
                    backgroundColor = Salmon600
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pencil),
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        }
    }
}

@Composable
private fun DialogContent(
    selectedItem: FilterType?,
    closeAction: () -> Unit,
    completeAction: () -> Unit,
    viewModel: CareViewModel,
    scaffoldState: ScaffoldState
) {
    when (selectedItem) {
        is FilterType.Sorting -> {
            return SortingBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = FilterItemsType.Sorting(selectedItem.items.list),
                onClickClose = {
                    closeAction()
                },
                onClickComplete = {
                    completeAction()
                    viewModel.updateSortingFilter(it)
                }
            )
        }

        is FilterType.Caring -> {
            return CaringBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = FilterItemsType.Caring(
                    list = selectedItem.items.list,
                ),
                onClickClose = { closeAction() },
                onClickComplete = {
                    completeAction()
                    viewModel.updateCaringFilter(it)
                },
                scaffoldState = scaffoldState
            )
        }

        is FilterType.Time -> {
            return TimeBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.items,
                onClickClose = { closeAction() },
                onClickComplete = {
                    closeAction()
                    viewModel.updateTimeFilter(it)
                }
            )
        }

        is FilterType.Period -> {
            return PeriodBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.items,
                onClickClose = { closeAction() },
                onClickComplete = {
                    closeAction()
                    viewModel.updatePeriodFilter(it)
                }
            )
        }

        is FilterType.Day -> {
            return DayBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.items,
                onClickClose = { closeAction() },
                onClickComplete = {
                    completeAction()
                    viewModel.updateDayFilter(it)
                },
                scaffoldState = scaffoldState
            )
        }

        is FilterType.NeighborhoodScope -> {
            return NeighborhoodScopeBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.items,
                onClickClose = { closeAction() },
                onClickComplete = {
                    completeAction()
                    viewModel.updateNeighborhoodScopeFilter(it)
                }
            )
        }

        else -> {
            Box {}
        }
    }
}

private fun navigateToWriteScreen(
    navController: NavHostController,
    summaryTabType: SummaryTabType?
) {
    when (summaryTabType) {
        SummaryTabType.JOB_OFFER -> NavigationUtils.navigate(navController, JobOfferWriteNav.route)
        SummaryTabType.JOB_SEEKER -> NavigationUtils.navigate(navController, JobSeekerWriteNav.route)
        SummaryTabType.COMPANY -> NavigationUtils.navigate(navController, CompanyWriteNav.route)
        else -> {}
    }
}

