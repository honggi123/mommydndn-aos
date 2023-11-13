package com.mommydndn.app.ui.features.care

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.data.model.care.Filter.FilterType
import com.mommydndn.app.data.model.common.TabSize
import com.mommydndn.app.ui.components.box.JobOfferSummaryBox
import com.mommydndn.app.ui.components.chip.ChipWithBottomArrow
import com.mommydndn.app.ui.components.common.CustomTab
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.modal.care.CaringBottomModal
import com.mommydndn.app.ui.components.modal.care.DayBottomModal
import com.mommydndn.app.ui.components.modal.care.NeighborhoodScopeBottomModal
import com.mommydndn.app.ui.components.modal.care.PeriodBottomModal
import com.mommydndn.app.ui.components.modal.layout.BaseModalBottomSheetLayout
import com.mommydndn.app.ui.components.modal.care.SortingBottomModal
import com.mommydndn.app.ui.components.modal.care.TimeBottomModal
import com.mommydndn.app.ui.navigation.JobOfferWriteNav
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading800
import com.mommydndn.app.utils.NavigationUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CareScreen(
    navController: NavHostController,
    viewModel: CareViewModel = hiltViewModel()
) {
    val userInfo by viewModel.userInfo.collectAsState()
    val pagingJobOfferSummary = viewModel.searchedJobOfferSummary.collectAsLazyPagingItems()
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
            getDialogContent(
                selectedItem = selectedItem,
                closeAction = {
                    scope.launch { sheetState.hide() }
                },
                viewModel = viewModel
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(leftContent = {
                    Text(
                        text = userInfo?.emd?.sigName ?: "",
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
                CustomTab(
                    size = TabSize.LARGE,
                    onTabClick = {},
                    tabs = listOf("구인글", "시터님", "안심업체")
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
                        .fillMaxWidth()
                        .height(584.dp)
                        .padding(top = 6.dp)
                        .background(White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(pagingJobOfferSummary) { index, item ->
                        if (item != null) {
                            JobOfferSummaryBox(item = item)
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
                    onClick = { NavigationUtils.navigate(navController, JobOfferWriteNav.route) },
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
private fun getDialogContent(
    selectedItem: FilterType?,
    closeAction: () -> Unit,
    viewModel: CareViewModel
) {
    when (selectedItem) {
        is FilterType.Sorting -> {
            return SortingBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = FilterItemsType.Sorting(selectedItem.itemsType.list),
                onClickClose = {
                    closeAction()
                },
                onClickComplete = {
                    closeAction()
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
                    isAllChecked = selectedItem.itemsType.isAllChecked,
                    list = selectedItem.itemsType.list,
                ),
                onClickClose = { closeAction() },
                onClickComplete = {
                    closeAction()
                    viewModel.updateCaringFilter(it)
                }
            )
        }

        is FilterType.Time -> {
            return TimeBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.itemsType,
                onClickClose = { closeAction() }
            )
        }

        is FilterType.Period -> {
            return PeriodBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.itemsType,
                onClickClose = { closeAction() }
            )
        }

        is FilterType.Day -> {
            return DayBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.itemsType,
                onClickClose = { closeAction() },
                onClickComplete = {
                    closeAction()
                    viewModel.updateDayFilter(it)
                }
            )
        }

        is FilterType.NeighborhoodScope -> {
            return NeighborhoodScopeBottomModal(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                ),
                item = selectedItem.itemsType,
                onClickClose = { closeAction() },
                onClickComplete = { closeAction() }
            )
        }

        else -> {
            Box {}
        }
    }
}

