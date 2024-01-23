package com.mommydndn.app.ui.care.details.worker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.CareWorkerOtherCondition
import com.mommydndn.app.ui.care.details.agency.reviews
import com.mommydndn.app.ui.care.details.components.CareDetailsAbout
import com.mommydndn.app.ui.care.details.components.CareDetailsBio
import com.mommydndn.app.ui.care.details.components.CareDetailsTopAppBar
import com.mommydndn.app.ui.care.details.components.DetailsCareTypes
import com.mommydndn.app.ui.care.details.components.DetailsReviewUiModel
import com.mommydndn.app.ui.care.details.components.DetailsReviews
import com.mommydndn.app.ui.care.details.components.DetailsViewMore
import com.mommydndn.app.ui.care.details.components.LikeAndActionButton
import com.mommydndn.app.ui.care.details.components.divider
import com.mommydndn.app.ui.care.list.components.AvailableNeighborhood
import com.mommydndn.app.ui.care.list.components.CareStatistics
import com.mommydndn.app.ui.care.list.components.DndnScore
import com.mommydndn.app.ui.care.list.components.ProfileImage
import com.mommydndn.app.ui.care.list.worker.CareWorkerListItem
import com.mommydndn.app.ui.care.list.worker.CareWorkerUiModel
import com.mommydndn.app.ui.components.tab.SmallTab
import com.mommydndn.app.ui.home.components.WriteReviewSection
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import java.time.LocalDate

@Composable
fun CareWorkerDetailsScreen() {}

@Composable
internal fun CareWorkerDetailsContent(
    onBackClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onBlockClick: () -> Unit,
    profileImageUrl: String,
    dndnCertified: Boolean,
    nickname: String,
    neighborhood: String,
    dndnScore: Float,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    bio: String,
    verifications: List<String>,
    otherConditions: List<CareWorkerOtherCondition>,
    careTypes: List<CareType>,
    reviews: List<DetailsReviewUiModel>,
    onViewAllReviewsClick: () -> Unit,
    moreWorkers: List<CareWorkerUiModel>,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onApplyToClick: () -> Unit,
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
                    WorkerProfile(
                        profileImageUrl = profileImageUrl,
                        dndnCertified = dndnCertified,
                        nickname = nickname,
                        neighborhood = neighborhood,
                        dndnScore = dndnScore,
                        matchingCount = matchingCount,
                        reviewCount = reviewCount,
                        responseRate = responseRate,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
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
                            name = nickname,
                            bio = bio,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                        )

                        Divider(
                            modifier = Modifier.padding(horizontal = 24.dp),
                            color = Grey50,
                            thickness = 1.5.dp,
                        )
                    }
                }

                val sectionPadding = PaddingValues(
                    start = 24.dp,
                    top = 28.dp,
                    end = 24.dp,
                    bottom = 24.dp
                )

                item {
                    CareDetailsAbout(
                        name = nickname,
                        verifications = verifications,
                        // TODO
                        registeredAt = LocalDate.now(),
                        pay = "희망시급 12,000원 ~ 40,000원",
                        otherConditions = otherConditions,
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
                        name = nickname,
                        reviews = reviews,
                        onViewAllClick = onViewAllReviewsClick,
                        modifier = Modifier.padding(sectionPadding)
                    )
                }

                divider()

                item {
                    DetailsViewMore(
                        title = stringResource(R.string.looking_for_teacher_care_worker),
                        content = {
                            moreWorkers.forEach { worker -> 
                                with(worker) {
                                    CareWorkerListItem(
                                        profileImageUrl = worker.profileImageUrl,
                                        dndnCertified = worker.dndnCertified,
                                        nickname = worker.nickname,
                                        neighborhood = worker.neighborhood,
                                        dndnScore = worker.dndnScore,
                                        isLiked = worker.isLiked,
                                        onLikeClick = onLikeClick,
                                        ageRangeAndGender = ageRangeAndGender,
                                        careTypes = worker.careTypes,
                                        matchingCount = worker.matchingCount,
                                        reviewCount = worker.reviewCount,
                                        responseRate = worker.responseRate,
                                    )
                                }
                            }
                        },
                        contentSpacing = 24.dp,
                        viewMoreText = stringResource(R.string.view_more_care_workers),
                        onViewMoreClick = { /*TODO*/ }
                    )
                }

                divider()

                item {
                    WriteReviewSection(onReviewClick = {})
                }
            }

            LikeAndActionButton(
                isLiked = isLiked,
                onLikeClick = onLikeClick,
                actionName = stringResource(R.string.apply_to),
                onActionClick = onApplyToClick,
                modifier = Modifier.background(White),
            )
        }
    }
}

@Composable
private fun WorkerProfile(
    profileImageUrl: String,
    dndnCertified: Boolean,
    nickname: String,
    neighborhood: String,
    dndnScore: Float,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.padding(6.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            ProfileImage(
                url = profileImageUrl,
                dndnCertified = dndnCertified,
                modifier = Modifier,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1F),
            ) {
                Text(
                    text = nickname,
                    color = Grey800,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold
                    )
                )

                AvailableNeighborhood(neighborhood = neighborhood)

                DndnScore(score = dndnScore)
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

@Preview
@Composable
private fun CareJobDetailsPreview() {
    CareWorkerDetailsContent(
        onBackClick = {},
        onInquiryClick = {},
        onBlockClick = {},
        profileImageUrl = "https://www.google.com/#q=equidem",
        dndnCertified = true,
        nickname = "세아쌤",
        neighborhood = "반포동 외 24개 동네",
        dndnScore = 5.0F,
        matchingCount = 24,
        reviewCount = 14,
        responseRate = 100,
        bio = "타인을 돕고 아이들을 돌볼때 가장 보람을 느끼는 제가, 동네 분들의 가정에 작은 도움이 될 수 있다면 너무나도 행복할 것 같아요 :)",
        verifications = listOf(
            "서초동 엄마 인증 완료",
            "선생님 인증 완료",
            "보육교사 인증 완료",
            "서울대학교 인증 완료",
        ),
        otherConditions = listOf(),
        careTypes = listOf(
            CareType.SchoolTransportation,
            CareType.SeniorCare
        ),
        reviews = reviews,
        onViewAllReviewsClick = {},
        moreWorkers = listOf(
            CareWorkerUiModel(
                profileImageUrl = "http://www.bing.com/search?q=menandri",
                dndnCertified = false,
                nickname = "수아",
                neighborhood = "반포동 외 9개 동네",
                dndnScore = 5.0F,
                isLiked = false,
                ageRangeAndGender = "20대 여성",
                careTypes = setOf(
                    CareType.Housekeeping,
                    CareType.SeniorCare,
                ),
                matchingCount = 24,
                reviewCount = 14,
                responseRate = 100,
            ),
            CareWorkerUiModel(
                profileImageUrl = "http://www.bing.com/search?q=menandri",
                dndnCertified = true,
                nickname = "유린",
                neighborhood = "반포동 외 4개 동네",
                dndnScore = 5.0F,
                isLiked = true,
                ageRangeAndGender = "30대 여성",
                careTypes = setOf(
                    CareType.ChildCare,
                    CareType.SchoolTransportation,
                ),
                matchingCount = 24,
                reviewCount = 14,
                responseRate = 100,
            )
        ),
        isLiked = false,
        onLikeClick = {},
        onApplyToClick = {},
        modifier = Modifier.background(White),
    )
}