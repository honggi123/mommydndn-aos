package com.mommydndn.app.ui.feature.care.joboffer.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.mommydndn.app.ui.components.common.AddressListItem
import com.mommydndn.app.ui.components.common.Searchbar


@Composable
fun JobOfferWriteNearestScreen(
    navController: NavHostController,
    viewModel: JobOfferWriteViewModel = hiltViewModel()
) {
    val keyword by viewModel.keyword.collectAsState()

    val pagingItemsByKeyword = viewModel.searchedTownsFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        Searchbar(
            keyword = keyword,
            onValueChange = {
                viewModel.setKeyword(it)
            },
            clearAction = {
                viewModel.setKeyword("")
            },
            placeHolderText = "도로명, 건물명, 번지로 검색해주세요",
            backStackAction = { navController.popBackStack() },
            searchAction = { }
        )
        LazyColumn {
            items(pagingItemsByKeyword.itemCount) { index ->
                val item = pagingItemsByKeyword[index]
                AddressListItem(
                    streetNum = item?.id ?: 0,
                    roadAddressText = item?.fullName ?: "",
                    streetAddressText = item?.ctprvnName ?: ""
                )
            }
        }
    }
}