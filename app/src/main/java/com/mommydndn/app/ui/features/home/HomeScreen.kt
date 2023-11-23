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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.ui.navigation.MainNav
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.SubtextBoxSize
import com.mommydndn.app.ui.components.list.BannerList
import com.mommydndn.app.ui.components.box.FooterBox
import com.mommydndn.app.ui.components.box.JobOfferBox
import com.mommydndn.app.ui.components.box.MarketListItemBox
import com.mommydndn.app.ui.components.box.SitterBox
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.SubBanner
import com.mommydndn.app.ui.components.modal.NoticeSettingListModal
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.GreyOpacity400
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.util.NavigationUtils
import kotlinx.coroutines.launch

const val MAX_MORE_BABY_ITEM_PAGE = 4

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainHomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val noticeSettings by viewModel.noticeSettings.collectAsState()
    val banners by viewModel.banners.collectAsState()
    val jobSeekers by viewModel.jobSeekers.collectAsState()
    val jobOffers by viewModel.jobOffers.collectAsState()
    val babyItems by viewModel.babyItems.collectAsState()
    val babyItemsPagingMeta by viewModel.babyItemsPagingMeta.collectAsState()

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
            )
        }, rightContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_headset),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
            )
        }
        )

        Column(
            modifier = Modifier.verticalScroll(
                scrollState
            )
        ) {
            BannerList(modifier = Modifier.fillMaxWidth(), items = banners)

            SubtextBox(
                modifier = Modifier.fillMaxWidth(),
                size = SubtextBoxSize.L,
                titleText = "가장 가까운 시터님",
                rightButtonText = "전체보기"
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, top = 28.dp, bottom = 36.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(jobSeekers) { item ->
                    SitterBox(item = item)
                }
            }
            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            SubtextBox(
                size = SubtextBoxSize.L,
                titleText = "도움이 필요한 주변 이웃",
                rightButtonText = "더보기",
                rightButtonOnClick = {
                    NavigationUtils.navigate(navController, MainNav.Care.route)
                }
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

            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            SubtextBox(size = SubtextBoxSize.L, titleText = "집 앞 육아용품 장터")

            Box(modifier = Modifier.fillMaxWidth()) {
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
                                .padding(24.dp)
                        )
                    }
                }
            }

            if (babyItemsPagingMeta.currentPageNum <= MAX_MORE_BABY_ITEM_PAGE
                && babyItemsPagingMeta.totalCount > 6
                && ((babyItemsPagingMeta.currentPageNum - 1) * MORE_BABY_ITEM_PAGE_SIZE) + 6 < babyItemsPagingMeta.totalCount
            ) {
                Button(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFFF0F2F4))
                        .fillMaxWidth(),
                    onClick = {
                        viewModel.fetchMoreBabyItems(currentCount = babyItemsPagingMeta.currentPageNum)
                    }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 20.dp),
                        text = "더보기",
                        style = MaterialTheme.typography.paragraph300.copy(
                            fontWeight = FontWeight.Normal,
                            color = Salmon600
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Divider(
                color = Grey50,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            SubBanner(modifier = Modifier.fillMaxWidth())
            FooterBox(modifier = Modifier.fillMaxWidth()) {}
        }
    }

    val sheetState =
        rememberModalBottomSheetState(
            ModalBottomSheetValue.Expanded,
            skipHalfExpanded = true,
            animationSpec = spring(
                dampingRatio = 0.85f,
                stiffness = 100f
            )
        )
    val scope = rememberCoroutineScope()

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
                    onDismiss = {
                        scope.launch { sheetState.hide() }
                    },
                    onItemSelected = { index, isChecked ->

                    },
                    onComplete = { },
                    itemList = noticeSettings,
                    titleCheckBoxText = "꼭 필요한 알림만 보내드릴게요"
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