package com.mommydndn.app.ui.care.details.agency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareAgencyOtherCondition.AfterSalesGuranteed
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.details.components.CareDetailsAbout
import com.mommydndn.app.ui.care.details.components.CareDetailsBio
import com.mommydndn.app.ui.care.details.components.CareDetailsDndnScore
import com.mommydndn.app.ui.care.details.components.CareDetailsLikeAndActionButton
import com.mommydndn.app.ui.care.details.components.CareDetailsTopAppBar
import com.mommydndn.app.ui.care.details.components.CareDetailsViewMore
import com.mommydndn.app.ui.care.details.components.CareDetailsCareTypes
import com.mommydndn.app.ui.care.details.components.DetailsReviewUiModel
import com.mommydndn.app.ui.care.details.components.DetailsReviews
import com.mommydndn.app.ui.care.details.components.divider
import com.mommydndn.app.ui.care.details.components.tagName
import com.mommydndn.app.ui.care.list.agency.CareAgencyListItem
import com.mommydndn.app.ui.care.list.agency.CareAgencyUiModel
import com.mommydndn.app.ui.care.list.agency.CertifiedAgency
import com.mommydndn.app.ui.care.list.components.AvailableNeighborhood
import com.mommydndn.app.ui.care.list.components.CareStatistics
import com.mommydndn.app.ui.components.banner.BannerPager
import com.mommydndn.app.ui.components.banner.BannerUiModel
import com.mommydndn.app.ui.components.tab.SmallTab
import com.mommydndn.app.ui.home.components.WriteReviewSection
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import java.time.LocalDate

@Composable
fun CareAgencyDetails() {

}

@Composable
private fun CareAgencyDetailsContent(
    onBackClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onBlockClick: () -> Unit,
    images: List<BannerUiModel>, // TODO: ImageUiModel
    profileImageUrl: String,
    dndnCertified: Boolean,
    agencyName: String,
    neighborhood: String,
    dndnScore: Float,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    bio: String,
    registeredAt: LocalDate,
    careTypes: List<CareType>,
    reviews: List<DetailsReviewUiModel>,
    onViewAllReviewsClick: () -> Unit,
    moreAgencies: List<CareAgencyUiModel>,
    onViewMoreAgenciesClick: () -> Unit,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onChatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.background(White)) {
            CareDetailsTopAppBar(
                onBackClick = onBackClick,
                onInquiryClick = onInquiryClick,
                onBlockClick = onBlockClick,
            )

            LazyColumn(modifier = Modifier.weight(1F)) {
                item {
                    Box {
                        BannerPager(
                            banners = images,
                            modifier = Modifier.background(Grey100),
                        )

                        AsyncImage(
                            model = profileImageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset { IntOffset(x = 32.dp.roundToPx(), y = 32.dp.roundToPx()) }
                                .background(Grey50, RoundedCornerShape(24.dp))
                                .size(80.dp),
                        )
                    }
                }

                item {
                    AgencyProfile(
                        dndnCertified = dndnCertified,
                        name = agencyName,
                        neighborhood = neighborhood,
                        dndnScore = dndnScore,
                        matchingCount = matchingCount,
                        reviewCount = reviewCount,
                        responseRate = responseRate,
                        modifier = Modifier.padding(
                            start = 24.dp,
                            top = 36.dp,
                            end = 24.dp,
                            bottom = 12.dp
                        )
                    )
                }

                stickyHeader {
                    // TODO
                    SmallTab(
                        tabs = listOf("소개", "돌봄분야", "받은 후기"),
                        selectedTabIndex = 0,
                        onTabClick = {},
                    )
                }

                item {
                    Column {
                        CareDetailsBio(
                            name = agencyName,
                            bio = bio,
                            modifier = Modifier.padding(24.dp),
                        )

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(horizontal = 24.dp),
                            color = Grey50,
                            thickness = 1.5.dp,
                        )
                    }
                }

                val sectionPadding = PaddingValues(horizontal = 24.dp, vertical = 28.dp)

                item {
                    CareDetailsAbout(
                        name = agencyName,
                        verifications = emptyList(),
                        // TODO
                        registeredAt = registeredAt,
                        pay = "평균월급 300만원 ~ 400만원 (수수료 10%)",
                        tags = listOf(AfterSalesGuranteed.tagName),
                        modifier = Modifier.padding(sectionPadding)
                    )
                }

                divider()

                item {
                    CareDetailsCareTypes(
                        careTypes = careTypes,
                        modifier = Modifier.padding(sectionPadding)
                    )
                }

                divider()

                item {
                    DetailsReviews(
                        name = agencyName,
                        reviews = reviews,
                        onViewAllClick = onViewAllReviewsClick,
                        modifier = Modifier.padding(sectionPadding),
                    )
                }

                divider()

                item {
                    CareDetailsViewMore(
                        title = stringResource(R.string.looking_for_more_agencies),
                        content = {
                            moreAgencies.forEach { agency ->
                                with(agency) {
                                    CareAgencyListItem(
                                        dndnCertified = this.dndnCertified,
                                        name = name,
                                        neighborhood = this.neighborhood,
                                        dndnScore = this.dndnScore,
                                        careTypes = this.careTypes,
                                        profileImageUrl = this.profileImageUrl,
                                        isLiked = isLiked,
                                        onLikeClick = {},
                                        matchingCount = this.matchingCount,
                                        reviewCount = this.reviewCount,
                                        responseRate = this.responseRate,
                                        modifier = Modifier
                                    )
                                }
                            }
                        },
                        contentSpacing = 32.dp,
                        viewMoreText = stringResource(R.string.view_more_care_agencies),
                        onViewMoreClick = onViewMoreAgenciesClick,
                    )
                }

                divider()

                item {
                    WriteReviewSection(onReviewClick = {})
                }
            }

            CareDetailsLikeAndActionButton(
                isLiked = isLiked,
                onLikeClick = onLikeClick,
                actionName = stringResource(R.string.start_chat),
                onActionClick = onChatClick,
                modifier = Modifier.background(White),
            )
        }
    }
}

@Composable
private fun AgencyProfile(
    dndnCertified: Boolean,
    name: String,
    neighborhood: String,
    dndnScore: Float,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(1F),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (dndnCertified) {
                        CertifiedAgency()
                    }

                    Text(
                        text = name,
                        color = Grey800,
                        style = MaterialTheme.typography.paragraph300.merge(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    AvailableNeighborhood(neighborhood = neighborhood)
                }

                CareDetailsDndnScore(score = dndnScore)
            }

            CareStatistics(
                matchingCount = matchingCount,
                reviewCount = reviewCount,
                responseRate = responseRate,
                modifier = Modifier,
            )
        }
    }
}

internal val reviews = listOf(
    DetailsReviewUiModel(
        nickname = "경**",
        reviewedAt = LocalDate.now(),
        dndnScore = 5.0F,
        careTypes = listOf(CareType.ChildCare),
        content = "세아쌤과 노는 걸 아이들이 너무 좋아해서 너무 감동했어요 ㅎㅎ! 다음에도 잘부탁드립니다~"
    ),
    DetailsReviewUiModel(
        nickname = "이**",
        reviewedAt = LocalDate.now().minusMonths(4),
        dndnScore = 5.0F,
        careTypes = listOf(CareType.SeniorCare, CareType.Housekeeping),
        content = "세아쌤 덕분에 걱정없이 출장 다녀왔습니다~~ 정말 감사해요!"
    ),
)

@Preview
@Composable
private fun CareAgencyDetailsContentPreview() {
    MommydndnTheme {
        CareAgencyDetailsContent(
            onBackClick = {},
            onInquiryClick = {},
            onBlockClick = {},
            images = listOf(),
            profileImageUrl = "http://www.bing.com/search?q=posidonium",
            dndnCertified = true,
            agencyName = "피카부 베이비시터",
            neighborhood = "서초동 외 24개 동네",
            dndnScore = 5.0F,
            matchingCount = 24,
            reviewCount = 14,
            responseRate = 100,
            bio = "피카부베이비시터는 이런 부모님의 마음을 담아 아이를 사랑하고 배려하는 올바른 인성과 기본적 소양을 갖춘 베이비시터를 파견하는 베이비시터 전문 업체입니다.",
            registeredAt = LocalDate.now(),
            careTypes = CareType.entries,
            reviews = reviews,
            onViewAllReviewsClick = {},
            moreAgencies = listOf(
                CareAgencyUiModel(
                    dndnCertified = false,
                    name = "베이비시터 부모 마음",
                    neighborhood = "서초동 외 24개 동네",
                    dndnScore = 4.9F,
                    careTypes = CareType.entries.toSet(),
                    profileImageUrl = "http://www.bing.com/search?q=leo",
                    isLiked = false,
                    matchingCount = 821,
                    reviewCount = 624,
                    responseRate = 100,
                ),
                CareAgencyUiModel(
                    dndnCertified = true,
                    name = "아이가치 베이비시터",
                    neighborhood = "서초동 외 4개 동네",
                    dndnScore = 5.0F,
                    careTypes = setOf(CareType.ChildCare, CareType.SeniorCare),
                    profileImageUrl = "http://www.bing.com/search?q=leo",
                    isLiked = true,
                    matchingCount = 821,
                    reviewCount = 624,
                    responseRate = 100,
                )
            ),
            onViewMoreAgenciesClick = {},
            isLiked = false,
            onLikeClick = {},
            onChatClick = {},
            modifier = Modifier,
        )
    }
}