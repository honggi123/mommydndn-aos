package com.mommydndn.app.ui.features.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mommydndn.app.R
import com.mommydndn.app.data.model.babyitem.BabyItem
import com.mommydndn.app.data.model.babyitem.BabyItemMeta
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.SubtextBoxSize
import com.mommydndn.app.ui.components.list.BannerList
import com.mommydndn.app.ui.components.box.FooterBox
import com.mommydndn.app.ui.features.home.components.JobOfferBox
import com.mommydndn.app.ui.features.home.components.SitterBox
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.SubBanner
import com.mommydndn.app.ui.components.modal.NoticeSettingListModal
import com.mommydndn.app.ui.features.home.components.MarketListItemBox
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    onMoreJobOfferButtonClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val babyItemsUiState by viewModel.babyItemsUiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    when (val uiState = uiState) {
        is HomeUiState.Loading -> {
            // TODO
        }

        is HomeUiState.Success -> {

            MainHomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                itemsUiState = babyItemsUiState,
                onMoreJobOfferButtonClick = onMoreJobOfferButtonClick,
                loadNextBabyItemPage = { viewModel.fetchMoreBabyItems(it) }
            )

            BottomSheetModal(
                scope = scope,
                noticeSettings = uiState.notifications
            )
        }

        is HomeUiState.Failure -> {
            // TODO
        }
    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainHomeScreen(
    modifier: Modifier = Modifier,
    onMoreJobOfferButtonClick: () -> Unit,
    loadNextBabyItemPage: (Int) -> Unit,
    uiState: HomeUiState.Success,
    itemsUiState: HomeBabyItemUiState
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
    ) {
        HomeTopAppBar(
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            BannerList(
                modifier = Modifier.fillMaxWidth(),
                items = uiState.banners
            )

            JobSeekerContent(
                modifier = Modifier.fillMaxWidth(),
                jobSeekers = uiState.jobSeekers
            )

            HomeDivider(modifier = Modifier.fillMaxWidth())

            JobOfferContent(
                modifier = Modifier.fillMaxWidth(),
                jobOffers = uiState.jobOffers,
                navigateToCareScreen = { onMoreJobOfferButtonClick() }
            )

            HomeDivider(modifier = Modifier.fillMaxWidth())

            BabyItemsContent(
                modifier = Modifier.fillMaxWidth(),
                babyItemUiState = itemsUiState,
                loadNextPage = { loadNextBabyItemPage(it) }
            )

            HomeDivider(modifier = Modifier.fillMaxWidth())

            SubBanner(modifier = Modifier.fillMaxWidth())

            FooterBox(
                modifier = Modifier.fillMaxWidth(),
                onInquiryClick = {}
            )
        }
    }
}

@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier
) {
    Header(leftContent = {
        Image(
            painter = painterResource(id = R.drawable.icon_logo),
            contentDescription = "icon_logo",
            modifier = modifier
                .size(36.dp)
        )
    }, rightContent = {
        Image(
            painter = painterResource(id = R.drawable.icon_headset),
            contentDescription = "icon_headset",
            modifier = Modifier
                .size(36.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.icon_bell),
            contentDescription = "icon_bell",
            modifier = Modifier
                .size(36.dp)
        )
    }
    )
}

@Composable
fun JobSeekerContent(
    jobSeekers: List<JobSeeker>,
    modifier: Modifier = Modifier
) {
    if (jobSeekers.isEmpty()) {
        return
    }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SubtextBox(
            modifier = Modifier.fillMaxWidth(),
            size = SubtextBoxSize.L,
            titleText = stringResource(id = R.string.category_job_seekers_title),
            rightButtonText = stringResource(id = R.string.see_all)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 28.dp, bottom = 36.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(jobSeekers) { item ->
                SitterBox(item)
            }
        }
    }
}

@Composable
fun JobOfferContent(
    jobOffers: List<JobOffer> = emptyList(),
    modifier: Modifier = Modifier,
    navigateToCareScreen: () -> Unit
) {
    if (jobOffers.isEmpty()) {
        return
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SubtextBox(
            modifier = Modifier.fillMaxWidth(),
            size = SubtextBoxSize.L,
            titleText = stringResource(id = R.string.category_job_offers_title),
            rightButtonText = stringResource(id = R.string.see_more),
            rightButtonOnClick = { navigateToCareScreen() }
        )

        Box(modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 36.dp)) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(jobOffers) { item ->
                    JobOfferBox(item = item)
                }
            }
        }
    }

}

@Composable
fun BabyItemsContent(
    babyItemUiState: HomeBabyItemUiState,
    loadNextPage: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val babyItems = babyItemUiState.babyItems

    if (babyItems.isEmpty()) {
        return
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        SubtextBox(
            modifier = Modifier.fillMaxWidth(),
            size = SubtextBoxSize.L,
            titleText = stringResource(id = R.string.category_baby_items_title)
        )

        Box(modifier = modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp, vertical = 28.dp
                    ),
            ) {
                babyItems.chunked(2).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { item ->
                            MarketListItemBox(modifier = Modifier.weight(1f), item = item)
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                    )
                }
            }
        }

        when (babyItemUiState) {
            is HomeBabyItemUiState.Success -> {

                // 1.현재 페이지 <= MAX_MORE_BABY_ITEM_PAGE
                // 2.현재까지 아이템 총 개수에 추가 되어야하는 아이템 개수를 더했을 때 보다 다음 페이지까지의 총 개수가 더 적을 경우
                //  1 page -> 0 + 추가 되어야하는 아이템 개수 < 아이템의 총 개수
                //  2 page -> (1 * 추가 되어야하는 아이템 개수) + 추가 되어야하는 아이템 개수 < 아이템의 총 개수

                val shouldShowLoadMoreButton =
                    babyItemUiState.babyItemsPagingMeta.currentPageNum <= MAX_MORE_BABY_ITEM_PAGES
                            && ((babyItemUiState.babyItemsPagingMeta.currentPageNum - 1) * MORE_BABY_ITEM_SIZE) + MORE_BABY_ITEM_SIZE < babyItemUiState.babyItemsPagingMeta.totalCount

                if (shouldShowLoadMoreButton) {
                    Button(
                        modifier = Modifier
                            .border(width = 1.dp, color = Color(0xFFF0F2F4))
                            .fillMaxWidth(),
                        onClick = {
                            loadNextPage(babyItemUiState.babyItemsPagingMeta.currentPageNum)
                        }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, bottom = 20.dp),
                            text = stringResource(id = R.string.see_more),
                            style = MaterialTheme.typography.paragraph300.copy(
                                fontWeight = FontWeight.Normal,
                                color = Salmon600
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            is HomeBabyItemUiState.Loading -> {
                // TODO
            }
        }

    }
}

@Composable
fun HomeDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        color = Grey50,
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetModal(
    scope: CoroutineScope,
    noticeSettings: List<Notification>
) {

    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Expanded,
        skipHalfExpanded = true,
        animationSpec = spring(
            dampingRatio = 0.85f,
            stiffness = 100f
        )
    )

    if (noticeSettings.isNotEmpty()) {
        ModalBottomSheetLayout(
            modifier = Modifier,
            sheetState = sheetState,
            sheetContentColor = Color.Transparent,
            sheetBackgroundColor = Color.Transparent,
            scrimColor = GreyOpacity400,
            sheetElevation = 0.dp,
            sheetContent = {
                NoticeSettingListModal(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 100.dp),
                    onDismiss = { scope.launch { sheetState.hide() } },
                    onItemSelected = { index, isChecked ->
                        // TODO
                    },
                    onComplete = {
                        // TODO
                    },
                    itemList = noticeSettings,
                    titleCheckBoxText = stringResource(id = R.string.send_necessary_notifications)
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            )
        }
    }
}