package com.mommydndn.app.ui.features.care.joboffer.write.preview

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mommydndn.app.R
import com.mommydndn.app.data.model.care.CertificationType
import com.mommydndn.app.data.model.care.JobOfferPreview
import com.mommydndn.app.data.model.care.SalaryType
import com.mommydndn.app.data.model.care.WorkPeriodType
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
import com.mommydndn.app.data.model.common.ImageInputFieldType
import com.mommydndn.app.data.model.common.MinMaxRange
import com.mommydndn.app.ui.components.box.ContentBox
import com.mommydndn.app.ui.components.box.InfoBox
import com.mommydndn.app.ui.components.box.MapContainerBox
import com.mommydndn.app.ui.components.box.ReviewBox
import com.mommydndn.app.ui.components.box.SubtextBox
import com.mommydndn.app.ui.components.box.TitleSectionBox
import com.mommydndn.app.ui.components.button.CtaButton
import com.mommydndn.app.ui.components.button.MommyDndnButton
import com.mommydndn.app.ui.components.common.Header
import com.mommydndn.app.ui.components.common.ImageInputField
import com.mommydndn.app.ui.components.common.ProfileBar
import com.mommydndn.app.ui.components.common.ProfileSummary
import com.mommydndn.app.ui.components.common.SubHeader
import com.mommydndn.app.ui.extensions.addFocusCleaner
import com.mommydndn.app.ui.theme.Grey100
import com.mommydndn.app.ui.theme.Grey50
import com.mommydndn.app.ui.theme.Grey500
import com.mommydndn.app.ui.theme.Grey700
import com.mommydndn.app.ui.theme.White
import com.mommydndn.app.ui.theme.paragraph300
import com.mommydndn.app.ui.theme.paragraph400
import com.mommydndn.app.utils.DateTimeUtils
import com.mommydndn.app.utils.NumberUtils
import com.mommydndn.app.utils.PermissionUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.daum.mf.map.api.CameraPosition
import net.daum.mf.map.api.CameraUpdate
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun JobOfferPreviewScreen(
    jobOfferPreview: JobOfferPreview?,
    navController: NavHostController,
    viewModel: JobOfferPreviewViewModel = hiltViewModel()
) {

    val authorInfo by viewModel.authorInfo.collectAsState()

    val context = LocalContext.current
    val kakaoMapView = remember { MapView(context) }

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
                text = "구인글 미리보기",
                style = MaterialTheme.typography.paragraph400.copy(
                    fontWeight = FontWeight.Bold,
                    color = Grey700
                )
            )
        })
        SubHeader(text = "시터님께 보여질 화면을 미리 보여드려요")
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                ProfileBar(
                    modifier = Modifier.fillMaxWidth(),
                    nameText = authorInfo?.nickname ?: "",
                    dndnScore = authorInfo?.dndnScore ?: 0.0,
                    locationText = authorInfo?.emd?.fullName ?: ""
                )
                TitleSectionBox(
                    modifier = Modifier.fillMaxWidth(),
                    titleText = jobOfferPreview?.title ?: "",
                    badgeStringList = jobOfferPreview?.caringTypeList?.map { it.value }
                        ?: emptyList(),
                    timeText = ""
                )

                val allDateText = if (jobOfferPreview?.taskType == WorkPeriodType.REGULAR) {
                    DateTimeUtils.formatLocalDateRange(
                        startDate = jobOfferPreview?.startDate,
                        endDate = jobOfferPreview?.endDate
                    )
                } else {
                    val formattedDates =
                        jobOfferPreview?.dateList?.map { DateTimeUtils.formatToMonthDay(it) }
                    formattedDates?.joinToString(", ") ?: ""
                }


                InfoBox(
                    modifier = Modifier.fillMaxWidth(),
                    salaryText = "${jobOfferPreview?.salaryType?.value ?: ""} ${
                        jobOfferPreview?.salary?.let {
                            NumberUtils.getPriceString(it)
                        }
                    }",
                    dateText = allDateText,
                    timeText = if (jobOfferPreview?.isTimeNegotiable == true) "협의 가능" else
                        "${DateTimeUtils.formatLocalTime(jobOfferPreview?.startTime)} ~ ${
                            DateTimeUtils.formatLocalTime(jobOfferPreview?.endTime)
                        }"
                )

                ContentBox(
                    infos = jobOfferPreview?.etcCheckedList?.map { it.displayName } ?: emptyList(),
                    photos = jobOfferPreview?.imageList?.map {
                        it.toUri()
                    } ?: emptyList(),
                    contentText = jobOfferPreview?.content ?: "",
                )

            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )
            SubtextBox(
                titleText = "찾아오시는 길"
            )
            Box(
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 24.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            ) {
                MapContainerBox(
                    modifier = Modifier.fillMaxWidth(),
                    mapView = kakaoMapView,
                    addressText = jobOfferPreview?.emd?.fullName ?: "",
                    latitude = jobOfferPreview?.latitude ?: 37.5666805,
                    longtitude = jobOfferPreview?.longitude ?: 126.9784147,
                    openMapAction = {
                        openKakaoMap(
                            context,
                            jobOfferPreview?.latitude ?: 37.5666805,
                            jobOfferPreview?.longitude ?: 126.9784147
                        )
                    }
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(40.dp)
            )
            SubtextBox(
                titleText = "워킹맘님은 이런 분이에요"
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProfileSummary(
                        modifier = Modifier.fillMaxWidth(),
                        profileUri = authorInfo?.profileUrl ?: "",
                        nameText = authorInfo?.nickname ?: "",
                        locationText = authorInfo?.emd?.sigName ?: "",
                        dndnScore = authorInfo?.dndnScore ?: 0.0,
                        isAuthenticated = authorInfo?.isDnDnAuthenticated ?: false,
                        dateText = authorInfo?.createdAt?.let {
                            DateTimeUtils.formatTimestampToYearMonthDay(it)
                        } ?: "",
                        matchCount = authorInfo?.matchingCount ?: 0,
                        reviewCount = authorInfo?.reviewCount ?: 0,
                        responseRate = authorInfo?.responseRate ?: "",
                        certificationList = authorInfo?.certificationList?.map {
                            it.certificationName
                        } ?: emptyList()
                    )
                    if (!authorInfo?.caringReviewList.isNullOrEmpty()) {
                        val latestReview = authorInfo?.caringReviewList?.get(0)
                        ReviewBox(
                            modifier = Modifier.fillMaxWidth(),
                            titleText = "가장 최근 후기",
                            dndnScore = latestReview?.rate ?: 0.0,
                            badgeStringList = latestReview?.caringTypeCodeList?.map { it.value }
                                ?: emptyList(),
                            dateText = latestReview?.createdAt?.let {
                                DateTimeUtils.formatTimestampToYearMonthDay(it)
                            } ?: "",
                            contentText = latestReview?.content ?: ""
                        )
                    }
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
                        if (jobOfferPreview != null) {
                            viewModel.createJobOffer(navController, context, jobOfferPreview)
                        }
                    }
                )
            }
        }
    }
}

private fun openKakaoMap(context: Context, latitude: Double, longitude: Double) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data =
        android.net.Uri.parse("kakaomap://look?p=${latitude},${longitude}")
    intent.addCategory(Intent.CATEGORY_BROWSABLE)

    try {
        context.getPackageManager()
            .getPackageInfo("net.daum.android.map", PackageManager.GET_ACTIVITIES)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data =
            android.net.Uri.parse("market://details?id=net.daum.android.map")
        context.startActivity(intent)
    }
}

