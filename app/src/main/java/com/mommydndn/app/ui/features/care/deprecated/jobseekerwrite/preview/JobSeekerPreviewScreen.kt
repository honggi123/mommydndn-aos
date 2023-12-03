package com.mommydndn.app.ui.features.care.deprecated.jobseekerwrite.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.JobSeekerPreview
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.ui.components.box.ReviewBox
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.UserIntroductionBox
import com.mommydndn.app.ui.components.button.MommyDndnButton
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.MediumProfileInfoStack
import com.mommydndn.app.ui.components.common.SittingCategory
import com.mommydndn.app.ui.components.common.SubHeader
import com.mommydndn.app.ui.components.list.SitterListItem
import com.mommydndn.app.ui.components.tab.SmallCustomTab
import com.mommydndn.app.ui.models.care.ProfileBoxType
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.util.DateTimeUtils
import com.mommydndn.app.util.NumberUtils

@Composable
fun JobSeekerPreviewScreen(
    jobSeekerPreview: JobSeekerPreview?,
    navController: NavHostController,
    viewModel: JobSeekerPreviewViewModel = hiltViewModel()
) {
    val authorInfo by viewModel.authorInfo.collectAsState()

    Column(
        modifier = Modifier
            .background(White)
    ) {

        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "",
                modifier = Modifier
                    .size(size = 36.dp)
                    .clickable { navController.popBackStack() }
            )
        }, centerContent = {
            Text(
                text = "구인 프로필 미리보기",
                style = MaterialTheme.typography.paragraph400.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700
                )
            )
        })

        SubHeader(text = "동네 분들께 보여질 화면을 미리 보여드려요")

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
                SitterListItem(
                    modifier = Modifier.fillMaxWidth(),
                    profileUrl = authorInfo?.profileUrl ?: "",
                    isDndnAuthenticated = authorInfo?.isDnDnAuthenticated ?: false,
                    nickname = authorInfo?.nickname ?: "",
                    neighborhood = authorInfo?.emd?.name ?: "",
                    responseRate = authorInfo?.responseRate ?: "",
                    matchingCount = authorInfo?.matchingCount ?: 0,
                    reviewCount = authorInfo?.reviewCount ?: 0,
                    profileType = ProfileBoxType.DETAIL
                )
            }

            SmallCustomTab(tabs = listOf("소개", "돌봄분야", "받은 후기"))

            Box(
                modifier = Modifier.padding(24.dp)
            ) {
                UserIntroductionBox(
                    modifier = Modifier.fillMaxWidth(),
                    title = "${authorInfo?.nickname}의 한마디",
                    content = jobSeekerPreview?.introduce ?: ""
                )
            }

            SubtextBox(
                titleText = "${authorInfo?.nickname}에 대하여"
            )

            val salaryText = if (jobSeekerPreview?.salaryType != SalaryType.NEGOTIATION) {
                "희망${jobSeekerPreview?.salaryType?.value} ${
                    jobSeekerPreview?.salary?.let {
                        NumberUtils.getPriceString(
                            it
                        )
                    }
                }"
            } else {
                null
            }

            MediumProfileInfoStack(
                modifier = Modifier.fillMaxWidth(),
                certificationList = authorInfo?.certificationList?.map { it.certificationName }
                    ?: emptyList(),
                infos = jobSeekerPreview?.etcCheckedList?.map { it.displayName } ?: emptyList(),
                salaryText = salaryText,
                dateText = authorInfo?.let {
                    "가입일 ${DateTimeUtils.formatTimestampToYearMonthDay(it.createdAt)}"
                } ?: ""
            )

            SubtextBox(
                titleText = "자신있게 도와드릴 수 있는 분야는"
            )
            Box(
                modifier = Modifier.padding(24.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 700.dp)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (jobSeekerPreview != null) {
                        items(jobSeekerPreview.caringTypeList) { item ->
                            SittingCategory(caringType = item)
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )

            SubtextBox(
                titleText = "${authorInfo?.nickname}에게 남긴 후기"
            )
            Box(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 24.dp,
                        start = 24.dp,
                        end = 24.dp
                    )
            ) {
                authorInfo?.caringReviewList?.forEach { item ->
                    ReviewBox(
                        modifier = Modifier.fillMaxWidth(),
                        titleText = item.nickname,
                        dndnScore = item.rate,
                        badgeStringList = item.caringTypeCodeList.map { it.value },
                        dateText = DateTimeUtils.formatTimestampToYearMonth(item.createdAt),
                        contentText = item.content
                    )
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Grey100)
                    .padding(
                        start = 24.dp,
                        top = 16.dp,
                        bottom = 32.dp,
                        end = 24.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                MommyDndnButton(
                    text = "이대로 올리기",
                    color = ButtonColor.SALMON,
                    colorType = ButtonColorType.FILLED,
                    sizeType = ButtonSizeType.LARGE,
                    rangeType = MinMaxRange.MAX,
                    onClick = {
                        jobSeekerPreview?.let {
                            viewModel.createJobSeeker(
                                navController = navController,
                                jobSeekerPreview = jobSeekerPreview
                            )
                        }
                    }
                )
            }
        }
    }

}
