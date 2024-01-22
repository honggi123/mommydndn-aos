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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.ui.care.details.components.DetailsAbout
import com.mommydndn.app.ui.care.details.components.DetailsBio
import com.mommydndn.app.ui.care.details.components.DetailsCareTypes
import com.mommydndn.app.ui.care.details.components.DetailsReviewUiModel
import com.mommydndn.app.ui.care.details.components.DetailsReviews
import com.mommydndn.app.ui.care.details.components.DetailsTopAppBar
import com.mommydndn.app.ui.care.details.components.DetailsViewMore
import com.mommydndn.app.ui.care.list.agency.CareAgencyListItem
import com.mommydndn.app.ui.care.list.agency.CareAgencyUiModel
import com.mommydndn.app.ui.care.list.agency.CertifiedAgency
import com.mommydndn.app.ui.care.list.components.AvailableNeighborhood
import com.mommydndn.app.ui.care.list.components.CareStatistics
import com.mommydndn.app.ui.components.tab.SmallTab
import com.mommydndn.app.ui.home.components.BannerPager
import com.mommydndn.app.ui.home.components.BannerUiModel
import com.mommydndn.app.ui.home.components.WriteReviewSection
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400
import java.time.LocalDate

@Composable
fun CareAgencyDetailsScreen() {

}

@Composable
internal fun CareAgencyDetailsContent(
    onBackClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onBlockClick: () -> Unit,
    images: List<BannerUiModel>, // TODO: ImageUiModel
    profileImageUrl: String,
    dndnCertified: Boolean,
    agencyName: String,
    neighborhood: String,
    dndnScore: Double,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    bio: String,
    careTypes: List<CareType>,
    reviews: List<DetailsReviewUiModel>,
    onViewAllReviewsClick: () -> Unit,
    moreAgencies: List<CareAgencyUiModel>,
    onViewMoreAgenciesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.background(White)) {
            DetailsTopAppBar(
                onBackClick = onBackClick,
                onInquiryClick = onInquiryClick,
                onBlockClick = onBlockClick,
            )

            LazyColumn {
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
                    CareAgencyDetailsProfile(
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
                        DetailsBio(
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
                    DetailsAbout(
                        name = agencyName,
                        verifications = emptyList(),
                        // TODO
                        registeredAt = "가입일 2023년 1월 10일",
                        pay = "평균월급 300만원 ~ 400만원 (수수료 10%)",
                        tags = listOf("A/S 보장해요"),
                        modifier = Modifier.padding(sectionPadding)
                    )
                }

                divider()

                item {
                    DetailsCareTypes(
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
                        modifier = Modifier.padding(sectionPadding)
                    )
                }

                divider()

                item {
                    DetailsViewMore(
                        title = stringResource(R.string.looking_for_more_agency),
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
                        viewMoreText = stringResource(R.string.view_more_agency),
                        onViewMoreClick = onViewMoreAgenciesClick,
                        modifier = Modifier,
                    )
                }

                divider()

                item {
                    WriteReviewSection(onReviewClick = {})
                }
            }
        }
    }
}

internal fun LazyListScope.divider(
    modifier: Modifier = Modifier,
    color: Color = Grey50,
    thickness: Dp = 20.dp,
) {
    item {
        Divider(
            modifier = modifier,
            color = color,
            thickness = thickness
        )
    }
}

@Composable
internal fun CareAgencyDetailsProfile(
    dndnCertified: Boolean,
    name: String,
    neighborhood: String,
    dndnScore: Double,
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

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = dndnScore.toString(), // TODO
                        color = Color(0xFFF28005),
                        style = MaterialTheme.typography.paragraph400.merge(
                            fontWeight = FontWeight.Bold
                        ),
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_muscle),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Unspecified,
                        )

                        Text(
                            text = stringResource(R.string.dndn_score),
                            color = Grey600,
                            style = MaterialTheme.typography.caption100.merge(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
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
            dndnScore = 5.0,
            matchingCount = 24,
            reviewCount = 14,
            responseRate = 100,
            bio = "피카부베이비시터는 이런 부모님의 마음을 담아 아이를 사랑하고 배려하는 올바른 인성과 기본적 소양을 갖춘 베이비시터를 파견하는 베이비시터 전문 업체입니다.",
            careTypes = CareType.entries,
            reviews = listOf(
                DetailsReviewUiModel(
                    nickname = "경**",
                    reviewedAt = LocalDate.now(),
                    dndnScore = 5.0F,
                    careTypes = listOf(CareType.ChildCare),
                    review = "세아쌤과 노는 걸 아이들이 너무 좋아해서 너무 감동했어요 ㅎㅎ! 다음에도 잘부탁드립니다~"
                ),
                DetailsReviewUiModel(
                    nickname = "**",
                    reviewedAt = LocalDate.now().minusMonths(4),
                    dndnScore = 5.0F,
                    careTypes = listOf(CareType.SeniorCare, CareType.Housekeeping),
                    review = "세아쌤 덕분에 걱정없이 출장 다녀왔습니다~~ 정말 감사해요!"
                ),
            ),
            onViewAllReviewsClick = {},
            moreAgencies = listOf(
                CareAgencyUiModel(
                    dndnCertified = false,
                    name = "베이비시터 부모 마음",
                    neighborhood = "서초동 외 24개 동네",
                    dndnScore = 4.9,
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
                    dndnScore = 5.0,
                    careTypes = setOf(CareType.ChildCare, CareType.SeniorCare),
                    profileImageUrl = "http://www.bing.com/search?q=leo",
                    isLiked = true,
                    matchingCount = 821,
                    reviewCount = 624,
                    responseRate = 100,
                )
            ),
            onViewMoreAgenciesClick = {},
            modifier = Modifier,
        )
    }
}