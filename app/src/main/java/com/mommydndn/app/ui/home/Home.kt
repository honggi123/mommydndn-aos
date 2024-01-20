package com.mommydndn.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.ui.home.components.BannerPager
import com.mommydndn.app.ui.home.components.BannerUiModel
import com.mommydndn.app.ui.home.components.HomeFooter
import com.mommydndn.app.ui.home.components.HomeTopAppBar
import com.mommydndn.app.ui.home.components.NearbyCareJobUiModel
import com.mommydndn.app.ui.home.components.NearbyCareJobs
import com.mommydndn.app.ui.home.components.NearestCareWorkerUiModel
import com.mommydndn.app.ui.home.components.NearestCareWorkers
import com.mommydndn.app.ui.home.components.ReviewSection
import com.mommydndn.app.ui.home.components.nearbyJobOpenings
import com.mommydndn.app.ui.home.components.nearestCareProviders
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.White

@Composable
internal fun Home(
    onInquiryClick: () -> Unit,
    onNotificationClick: () -> Unit,
    banners: List<BannerUiModel>,
    nearestCareProviders: List<NearestCareWorkerUiModel>,
    onAllCareProvidersClick: () -> Unit,
    nearbyJobOpenings: List<NearbyCareJobUiModel>,
    onMoreJobOpeningsClick: () -> Unit,
    onReviewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(Grey50)
                .verticalScroll(state = rememberScrollState()),
        ) {
            HomeTopAppBar(
                onInquiryClick = onInquiryClick,
                onNotificationClick = onNotificationClick,
            )

            BannerPager(banners = banners)

            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                NearestCareWorkers(
                    nearestCareWorkers = nearestCareProviders,
                    onSeeAllClick = onAllCareProvidersClick,
                    modifier = Modifier.background(White),
                )

                NearbyCareJobs(
                    nearbyJobOpenings = nearbyJobOpenings,
                    onMoreClick = onMoreJobOpeningsClick,
                    modifier = Modifier.background(White),
                )

                ReviewSection(
                    onReviewClick = onReviewClick,
                    modifier = Modifier
                        .background(White)
                        .fillMaxWidth(),
                )

                HomeFooter(
                    onInquiryClick = onInquiryClick,
                    modifier = Modifier.background(White),
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home(
        onInquiryClick = { /*TODO*/ },
        onNotificationClick = { /*TODO*/ },
        banners = emptyList(),
        nearestCareProviders = nearestCareProviders,
        onAllCareProvidersClick = { /*TODO*/ },
        nearbyJobOpenings = nearbyJobOpenings,
        onMoreJobOpeningsClick = { /*TODO*/ },
        onReviewClick = { /*TODO*/ },
        modifier = Modifier.background(Grey50),
    )
}