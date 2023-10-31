package com.mommydndn.app.ui.features.care.joboffer.write.preview

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapType
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapViewInfo
import com.kakao.vectormap.camera.CameraUpdate
import com.mommydndn.app.R
import com.mommydndn.app.data.model.common.ButtonColor
import com.mommydndn.app.data.model.common.ButtonColorType
import com.mommydndn.app.data.model.common.ButtonSizeType
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun JobOfferPreviewScreen(
    postId: Int?,
    navController: NavHostController,
    viewModel: JobOfferPreviewViewModel = hiltViewModel()
) {

    Log.e("postId","postt::"+postId.toString())
    val context = LocalContext.current
    val kakaoMapView = MapView(context)

    val kakaoMapCallback = object : KakaoMapReadyCallback() {
        override fun onMapReady(kakaoMap: KakaoMap) {
            // 인증 후 API가 정상적으로 실행될 때 호출됨
        }

        override fun getPosition(): LatLng {
            // 지도 시작 시 위치 좌표를 설정
            return LatLng.from(37.406960, 127.115587)
        }

        override fun getZoomLevel(): Int {
            return 15
        }

        override fun getMapViewInfo(): MapViewInfo {
            return MapViewInfo.from("", MapType.NORMAL)
        }

        override fun getViewName(): String {
            return "MyFirstMap"
        }

        override fun isVisible(): Boolean {
            return true
        }

        override fun getTag(): String {
            // KakaoMap의 tag을 설정
            return "FirstMapTag"
        }
    }

    kakaoMapView.start(kakaoMapCallback)

    Column(
        modifier = Modifier
            .background(White)
    ) {

        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.ic_x),
                contentDescription = "",
                modifier = Modifier
                    .size(size = 36.dp)
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
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ProfileBar(
                    modifier = Modifier.fillMaxWidth(),
                    nameText = "워킹맘",
                    dndnScore = 5.5,
                    locationText = "반포동"
                )
                TitleSectionBox(
                    modifier = Modifier.fillMaxWidth(),
                    titleText = "2일간 풀타임으로 아이 둘 맡아주실 분 구해요.",
                    badgeStringList = listOf("육아"),
                    timeText = "1시간 전"
                )
                InfoBox(
                    modifier = Modifier.fillMaxWidth(),
                    salaryText = "시급 12,000원",
                    dateText = "8월 12일 ~ 13일 (토,일)",
                    timeText = "17:00 ~ 22:00"
                )
                ContentBox(infos = listOf("cctv 괜찮고", "반려동물을 좋아하고", "입주가능하고"))
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
                    mapView = kakaoMapView
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
                        profileUri = "",
                        nameText = "홍기",
                        locationText = "New York, NY",
                        dndnScore = 4.7,
                        isAuthenticated = true,
                        dateText = "2일전",
                        matchCount = 10,
                        reviewCount = 25,
                        responseRate = 95,
                        neighborhoodText = "서초동"
                    )
                    ReviewBox(
                        modifier = Modifier.fillMaxWidth(),
                        titleText = "가장 최근 후기",
                        dndnScore = 5.5,
                        badgeStringList = listOf("육아"),
                        dateText = "2023년 4월",
                        contentText = "어머님께서 인품이 선하시고 너무 친절하십니다~ ㅎㅎ "
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
                    onClick = {}
                )
            }
        }
    }
}


