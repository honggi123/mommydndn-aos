package com.mommydndn.app.ui.features.care

import android.annotation.SuppressLint
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.Filter.FilterItemsType
import com.mommydndn.app.data.model.care.Filter.FilterType
import com.mommydndn.app.data.model.common.TabSize
import com.mommydndn.app.ui.components.chip.ChipWithBottomArrow
import com.mommydndn.app.ui.components.common.CustomTab
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.modal.care.CaringBottomModal
import com.mommydndn.app.ui.components.modal.care.DayBottomModal
import com.mommydndn.app.ui.components.modal.care.PeriodBottomModal
import com.mommydndn.app.ui.components.modal.layout.BaseModalBottomSheetLayout
import com.mommydndn.app.ui.components.modal.care.SortingBottomModal
import com.mommydndn.app.ui.components.modal.care.TimeBottomModal
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.heading800
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CareScreen(
    navController: NavHostController,
    viewModel: CareViewModel = hiltViewModel()
) {
//    val pagingJobOfferSummary = viewModel.searchedJobOfferSummary.collectAsLazyPagingItems()
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
            getDialogContent(selectedItem, closeAction = { scope.launch { sheetState.hide() } })
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(leftContent = {
                Text(
                    text = "서초동",
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
//        LazyColumn(
//            modifier = Modifier
//                .width(390.dp)
//                .height(584.dp)
//                .padding(top = 6.dp)
//                .background(White),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            itemsIndexed(pagingJobOfferSummary) { index, item ->
//                if (item != null) {
//                    JobOfferSummaryBox(item = item)
//                }
//            }
//        }
        }
    }
}

@Composable
private fun getDialogContent(
    selectedItem: FilterType?,
    closeAction: () -> Unit,
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
                onClickClose = { closeAction() }
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
                onClickClose = { closeAction() }
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
                onClickClose = { closeAction() }
            )
        }

        else -> {
            Box {}
        }
    }
}

