package com.mommydndn.app.feature.home

import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mommydndn.app.R
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.domain.model.care.JobOffer
import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.feature.care.components.TopAppBarHeight
import com.mommydndn.app.ui.deprecated.components.box.FooterBox
import com.mommydndn.app.ui.deprecated.components.box.SubtextBox
import com.mommydndn.app.ui.deprecated.components.box.SubtextBoxSize
import com.mommydndn.app.ui.deprecated.components.common.SubBanner
import com.mommydndn.app.ui.deprecated.components.modal.NoticeSettingListModal
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel() // hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    when (uiState) {
        is HomeUiState.Loading -> {
            // TODO
        }

        is HomeUiState.Success -> {
        }

        is HomeUiState.Failure -> {
            // TODO
        }
    }
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    onMoreJobOfferButtonClick: () -> Unit,
    uiState: HomeUiState.Success,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
    ) {
        HomeTopAppBar(
            onInquiryClick = {},
            onNotificationClick = {},
            modifier = Modifier.fillMaxWidth(),
        )

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
//            BannerList(
//                modifier = Modifier.fillMaxWidth(),
//                items = uiState.banners
//            )
//
//            JobSeekerContent(
//                modifier = Modifier.fillMaxWidth(),
//                jobSeekers = uiState.jobSeekers
//            )
//
//            HomeDivider(modifier = Modifier.fillMaxWidth())
//
//            JobOfferContent(
//                modifier = Modifier.fillMaxWidth(),
//                jobOffers = uiState.jobOffers,
//                onMoreButtonClick = { onMoreJobOfferButtonClick() }
//            )
//
//            HomeDivider(modifier = Modifier.fillMaxWidth())

//            BabyItemsContent(
//                modifier = Modifier.fillMaxWidth(),
//                babyItemUiState = itemsUiState,
//                loadNextPage = { loadNextBabyItemPage(it) }
//            )
//
//            HomeDivider(modifier = Modifier.fillMaxWidth())

            SubBanner(modifier = Modifier.fillMaxWidth())

            FooterBox(
                modifier = Modifier.fillMaxWidth(),
                onInquiryClick = {}
            )
        }
    }
}

@Composable
private fun HomeTopAppBar(
    onInquiryClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(White)
            .fillMaxWidth()
            .height(TopAppBarHeight),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_logo),
            contentDescription = "HomeTopAppBar_Logo",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
                .size(36.dp),
            tint = Color.Unspecified,
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_headset),
                contentDescription = "HomeTopAppBar_Headset",
                modifier = Modifier
                    .size(36.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onInquiryClick,
                    ),
                tint = Color.Unspecified,
            )

            Icon(
                painter = painterResource(id = R.drawable.icon_bell),
                contentDescription = "HomeTopAppBar_Bell",
                modifier = Modifier
                    .size(36.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onNotificationClick,
                    ),
                tint = Color.Unspecified,
            )
        }
    }
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
            title = stringResource(id = R.string.category_job_seekers_title),
            subtitle = "",
            trailingLabel = stringResource(id = R.string.see_all)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 28.dp, bottom = 36.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(jobSeekers) { item ->
                // TODO
                // CareProviderProfile(item)
            }
        }
    }
}

@Composable
fun JobOfferContent(
    onMoreButtonClick: () -> Unit,
    jobOffers: List<JobOffer> = emptyList(),
    modifier: Modifier = Modifier,
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
            title = stringResource(id = R.string.category_job_offers_title),
            subtitle = "",
            trailingLabel = stringResource(id = R.string.see_more),
            onClick = { onMoreButtonClick() }
        )

        Box(modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 36.dp)) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                /*
items(jobOffers) { item ->
                    JobOfferBox(
                        modifier = Modifier.width(216.dp),
                        item = item
                    )
                }
                 */
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

@Preview
@Composable
private fun HomeScreen_Preview() {
    HomeScreen(
        onMoreJobOfferButtonClick = {},
        uiState = HomeUiState.Success(),
        modifier = Modifier.fillMaxSize()
    )
}