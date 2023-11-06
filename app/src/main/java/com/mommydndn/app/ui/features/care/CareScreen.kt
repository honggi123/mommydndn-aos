package com.mommydndn.app.ui.features.care

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.mommydndn.app.data.model.common.TabSize
import com.mommydndn.app.ui.components.box.JobOfferBox
import com.mommydndn.app.ui.components.box.JobOfferSummaryBox
import com.mommydndn.app.ui.components.chip.ChipWithBottomArrow
import com.mommydndn.app.ui.components.common.CustomTab
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.heading800

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CareScreen(
    navController: NavHostController,
    viewModel: CareViewModel = hiltViewModel()
) {
    val pagingJobOfferSummary = viewModel.searchedJobOfferSummary.collectAsLazyPagingItems()
    val filterItems by viewModel.filterItems.collectAsState()

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
                ChipWithBottomArrow(selected = item.isSelected, text = item.displayingName)
            }
        }
        LazyColumn(
            modifier = Modifier
                .width(390.dp)
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


}