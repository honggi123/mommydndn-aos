package com.mommydndn.app.ui.care.details.jobposting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage
import com.mommydndn.app.R
import com.mommydndn.app.domain.model.CareType
import com.mommydndn.app.domain.model.CaregiverPreference
import com.mommydndn.app.domain.model.Pay
import com.mommydndn.app.domain.model.PayPeriod
import com.mommydndn.app.domain.model.ShortTermWorkTime
import com.mommydndn.app.domain.model.WorkTime
import com.mommydndn.app.ui.care.details.components.CareDetailsDndnScore
import com.mommydndn.app.ui.care.details.components.CareDetailsLikeAndActionButton
import com.mommydndn.app.ui.care.details.components.CareDetailsRegisteredAt
import com.mommydndn.app.ui.care.details.components.CareDetailsSectionTitle
import com.mommydndn.app.ui.care.details.components.CareDetailsTags
import com.mommydndn.app.ui.care.details.components.CareDetailsTopAppBar
import com.mommydndn.app.ui.care.details.components.CareDetailsVerification
import com.mommydndn.app.ui.care.details.components.CareDetailsViewMore
import com.mommydndn.app.ui.care.details.components.DetailsReview
import com.mommydndn.app.ui.care.details.components.DetailsReviewUiModel
import com.mommydndn.app.ui.care.details.components.tagName
import com.mommydndn.app.ui.care.list.components.CareStatistics
import com.mommydndn.app.ui.care.list.components.displayName
import com.mommydndn.app.ui.care.list.jobposting.CaregiverJobPostingListItem
import com.mommydndn.app.ui.care.list.jobposting.CaregiverJobPostingUiModel
import com.mommydndn.app.ui.care.list.jobs
import com.mommydndn.app.ui.components.tag.TagLabel
import com.mommydndn.app.ui.home.components.WriteReviewSection
import com.mommydndn.app.ui.theme.DeepOrange
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey400
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey600
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.Grey800
import com.mommydndn.app.ui.theme.MommydndnTheme
import com.mommydndn.app.ui.theme.Orange100
import com.mommydndn.app.ui.theme.Salmon600
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.caption100
import com.mommydndn.app.ui.theme.caption200
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun CaregiverJobPostingDetails() {
}

data class CareJobEmployerUiModel(
    val profileImageUrl: String,
    val nickname: String,
    val dndnCertified: Boolean,
    val neighborhoodName: String,
    val dndnScore: Float,
    val verification: String,
    val registeredAt: LocalDate,
    val matchingCount: Int,
    val reviewCount: Int,
    val responseRate: Int,
    val latestReview: DetailsReviewUiModel,
)

data class CareJobDetailsUiModel(
    val employer: CareJobEmployerUiModel,
    val title: String,
    val careTypes: List<CareType>,
    val createdAt: LocalDateTime,
    val pay: Pay,
    val workTime: WorkTime,
    val content: String,
    val preferences: List<CaregiverPreference>,
    val imageUrls: List<String>,
    val applicantsCount: Int,
    val likesCount: Int,
    val viewsCount: Int,
)

@Composable
private fun CaregiverJobPostingDetailsContent(
    onBackClick: () -> Unit,
    onInquiryClick: () -> Unit,
    onBlockClick: () -> Unit,
    employer: CareJobEmployerUiModel,
    title: String,
    careTypes: List<CareType>,
    createdAt: LocalDateTime,
    pay: Pay,
    workTime: WorkTime,
    content: String,
    preferences: List<CaregiverPreference>,
    imageUrls: List<String>,
    applicantsCount: Int,
    likesCount: Int,
    viewsCount: Int,
    moreJobs: List<CaregiverJobPostingUiModel>,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onApplyToClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.background(White)) {
            CareDetailsTopAppBar(
                onBackClick = onBackClick,
                onInquiryClick = onInquiryClick,
                onBlockClick = onBlockClick,
            )

            Column(
                modifier = Modifier
                    .background(Grey50)
                    .weight(1F)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(White)
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    with(employer) {
                        EmployerProfile(
                            profileImageUrl = profileImageUrl,
                            nickname = nickname,
                            dndnCertified = dndnCertified,
                            neighborhoodName = neighborhoodName,
                            dndnScore = dndnScore,
                        )
                    }

                    Divider(color = Grey50, thickness = 1.5.dp)

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = title,
                            color = Grey800,
                            style = MaterialTheme.typography.paragraph400.merge(
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            careTypes.forEach { careType ->
                                TagLabel(
                                    text = careType.displayName,
                                    textColor = DeepOrange,
                                    backgroundColor = Orange100
                                )
                            }
                        }

                        Text(
                            text = createdAt.toString(), // TODO
                            color = Grey500,
                            style = MaterialTheme.typography.caption100
                        )
                    }

                    PayAndWorkAvailability(pay = pay, workTime = workTime)

                    Content(
                        content = content,
                        preferences = preferences,
                        imageUrls = imageUrls,
                        applicantsCount = applicantsCount,
                        likesCount = likesCount,
                        viewsCount = viewsCount,
                    )
                }

                with(employer) {
                    AboutEmployer(
                        nickname = nickname,
                        profileImageUrl = profileImageUrl,
                        dndnCertified = dndnCertified,
                        neighborhoodName = neighborhoodName,
                        dndnScore = dndnScore,
                        verification = verification,
                        registeredAt = registeredAt,
                        matchingCount = matchingCount,
                        reviewCount = reviewCount,
                        responseRate = responseRate,
                        review = latestReview,
                        onViewProfileClick = {},
                    )
                }


                CareDetailsViewMore(
                    title = stringResource(R.string.looking_for_more_care_jobs),
                    content = {
                        moreJobs.forEachIndexed { index, job ->
                            with(job) {
                                CaregiverJobPostingListItem(
                                    workPeriod = workPeriod,
                                    careTypes = job.careTypes,
                                    isClosed = isClosed,
                                    title = job.title,
                                    isLiked = job.isLiked,
                                    neighborhoodName = neighborhoodName,
                                    createdAt = job.createdAt,
                                    daysOfWeek = daysOfWeek,
                                    startTime = startTime,
                                    endTime = endTime,
                                    payPeriod = payPeriod,
                                    pay = job.pay,
                                )
                            }

                            if (index != moreJobs.lastIndex) {
                                Divider(
                                    color = Grey50,
                                    thickness = 1.5.dp
                                )
                            }
                        }
                    },
                    contentSpacing = 24.dp,
                    viewMoreText = stringResource(R.string.view_more_care_jobs),
                    onViewMoreClick = {},
                    modifier = Modifier.background(White)
                )

                WriteReviewSection(
                    onReviewClick = {}, // TODO
                    modifier = Modifier.background(White)
                )
            }

            CareDetailsLikeAndActionButton(
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
private fun EmployerProfile(
    profileImageUrl: String,
    nickname: String,
    dndnCertified: Boolean,
    neighborhoodName: String,
    dndnScore: Float,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = profileImageUrl,
            contentDescription = "프로필 사진",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Grey50),
            contentScale = ContentScale.Crop
        )

        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = nickname,
                    color = Grey700,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold
                    )
                )

                if (dndnCertified) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_certificate),
                        contentDescription = "든든 인증",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Unspecified
                    )
                }
            }

            Text(
                text = neighborhoodName,
                color = Grey500,
                style = MaterialTheme.typography.caption100
            )
        }

        Spacer(modifier = Modifier.weight(1F))

        CareDetailsDndnScore(score = dndnScore)
    }
}

@Composable
private fun PayAndWorkAvailability(
    pay: Pay,
    workTime: WorkTime,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Grey50, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_coin),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Grey400,
            )

            with(pay) {
                Text(
                    text = "${period.displayName} ${com.mommydndn.app.ui.care.list.jobposting.payFormatter.format(this.amount)}",
                    color = Grey700,
                    style = MaterialTheme.typography.paragraph300.merge(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_calendar_outline),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Grey400,
            )

            Text(
                text = "8월 12일 ~ 13일 (토, 일)", // TODO
                color = Grey600,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Medium
                )
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_clock),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Grey400,
            )

            Text(
                text = "17:00 ~ 22:00", // TODO
                color = Grey600,
                style = MaterialTheme.typography.caption200.merge(
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
private fun Content(
    content: String,
    preferences: List<CaregiverPreference>,
    imageUrls: List<String>,
    applicantsCount: Int,
    likesCount: Int,
    viewsCount: Int,
) {
    Column(
        modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        Text(
            text = content,
            color = Grey800,
            style = MaterialTheme.typography.paragraph300
        )

        CareDetailsTags(preferences.map { it.tagName })

        if (imageUrls.isNotEmpty()) {
            BoxWithConstraints {
                val cellSize = maxWidth / 3 - 2 * 6.dp

                val rowCount = (imageUrls.size - 1) / 3 + 1

                val height = cellSize * rowCount + (rowCount - 1) * 6.dp

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.height(height),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    items(imageUrls) { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(1F)
                                .clip(RoundedCornerShape(6.dp))
                                .background(Grey50),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        
        Row(
            modifier = Modifier.align(Alignment.End),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.applicants_count, applicantsCount),
                color = Grey500,
                style = MaterialTheme.typography.caption100
            )

            Box(modifier = Modifier
                .background(Grey400, CircleShape)
                .size(2.dp))

            Text(
                text = stringResource(R.string.likes_count, likesCount),
                color = Grey500,
                style = MaterialTheme.typography.caption100
            )

            Box(modifier = Modifier
                .background(Grey400, CircleShape)
                .size(2.dp))

            Text(
                text = stringResource(R.string.views_count, viewsCount),
                color = Grey500,
                style = MaterialTheme.typography.caption100
            )
        }
    }
}

@Composable
private fun AboutEmployer(
    nickname: String,
    profileImageUrl: String,
    dndnCertified: Boolean,
    neighborhoodName: String,
    dndnScore: Float,
    verification: String,
    registeredAt: LocalDate,
    matchingCount: Int,
    reviewCount: Int,
    responseRate: Int,
    review: DetailsReviewUiModel,
    onViewProfileClick: () -> Unit,
) {
    Column(Modifier.background(White)) {
        Column(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp,
            )
        ) {
            CareDetailsSectionTitle(
                title = stringResource(R.string.about_employer, nickname),
                modifier = Modifier.padding(vertical = 28.dp),
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                EmployerProfile(
                    profileImageUrl = profileImageUrl,
                    nickname = nickname,
                    dndnCertified = dndnCertified,
                    neighborhoodName = neighborhoodName,
                    dndnScore = dndnScore,
                )

                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    CareDetailsVerification(verification)

                    CareDetailsRegisteredAt(registeredAt)
                }

                CareStatistics(
                    matchingCount = matchingCount,
                    reviewCount = reviewCount,
                    responseRate = responseRate,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            with(review) {
                DetailsReview(
                    nickname = stringResource(R.string.latest_review),
                    reviewedAt = reviewedAt,
                    dndnScore = review.dndnScore,
                    careTypes = careTypes,
                    content = content
                )
            }
        }

        Divider(color = Grey100, thickness = 1.dp)

        Text(
            text = stringResource(R.string.view_profile),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = onViewProfileClick)
                .padding(vertical = 20.dp),
            color = Salmon600,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.paragraph300
        )
    }
}

@Preview
@Composable
private fun CareJobDetailsPreview() {
    val content = "안녕하세요 ~ \n" +
            "6세 여아와 4세 남아를 키우는 워킹맘입니다!\n" +
            "\n" +
            "이번 주말 2일간 출장을 가게 되어 시터 선생님을 구하게 되었어요~ 1일 당 8시간, 2일 간 맡아주실 분을 구하고 있습니다!\n" +
            "\n" +
            "시급은 일단 12,000원으로 올려놓았지만 \n" +
            "선생님 경력에 따라 협의도 가능합니다 ㅎㅎ \n" +
            "\n" +
            "아무래도 두 아이인 만큼 육아 난이도가 있다고 생각하기에, 고수엄마이신 분이 지원해주시면 감사드리겠습니다 :) ~\n" +
            "\n" +
            "부담없이 많은 지원 부탁드려요!"

    MommydndnTheme {
        CaregiverJobPostingDetailsContent(
            onBackClick = {},
            onInquiryClick = {},
            onBlockClick = {},
            employer = CareJobEmployerUiModel(
                profileImageUrl = "https://duckduckgo.com/?q=assueverit",
                nickname = "워킹맘",
                dndnCertified = true,
                neighborhoodName = "반포동",
                dndnScore = 5.0F,
                verification = "서초동 엄마 인증 완료",
                registeredAt = LocalDate.now(),
                matchingCount = 5,
                reviewCount = 3,
                responseRate = 100,
                latestReview = DetailsReviewUiModel(
                    nickname = "이**",
                    reviewedAt = LocalDate.of(2023, 4, 16),
                    dndnScore = 5.0F,
                    careTypes = listOf(
                        CareType.ChildCare,
                        CareType.Housekeeping,
                        CareType.SchoolTransportation
                    ),
                    content = "어머님께서 인품이 선하시고 너무 친절하십니다~ ㅎㅎ \n" +
                            "아이들도 정말 귀엽고 순해요! 다음에도 또 뵈면 좋겠네요~"
                )
            ),
            title = "2일간 풀타임으로 아이 둘 맡아주실 분 구해요",
            careTypes = listOf(
                CareType.ChildCare,
                CareType.Housekeeping,
                CareType.SchoolTransportation
            ),
            createdAt = LocalDateTime.now(),
            pay = Pay(
                period = PayPeriod.Hourly,
                amount = 12000
            ),
            workTime = ShortTermWorkTime(
                dates = listOf(
                    LocalDate.of(2023, 8, 12),
                    LocalDate.of(2023, 8, 13),
                ),
                startTime = LocalTime.of(17, 0),
                endTime = LocalTime.of(22, 0),
                flexible = false
            ),
            content = content,
            preferences = CaregiverPreference.entries,
            imageUrls = listOf(""),
            applicantsCount = 3,
            likesCount = 34,
            viewsCount = 230,
            moreJobs = jobs,
            isLiked = false,
            onLikeClick = {},
            onApplyToClick = {},
            modifier = Modifier
        )
    }
}