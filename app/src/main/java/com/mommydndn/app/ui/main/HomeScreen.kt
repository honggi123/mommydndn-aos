package com.mommydndn.app.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mommydndn.app.data.model.Banner
import com.mommydndn.app.data.model.CommunityPost
import com.mommydndn.app.data.model.JobOfferInfo
import com.mommydndn.app.data.model.MarketListItem
import com.mommydndn.app.data.model.SitterProfile
import com.mommydndn.app.ui.component.common.SubtextBox
import com.mommydndn.app.ui.component.common.SubtextBoxSize
import com.mommydndn.app.ui.component.home.BannerList
import com.mommydndn.app.ui.component.home.CommunityPostBox
import com.mommydndn.app.ui.component.home.Footer
import com.mommydndn.app.ui.component.home.JobOfferInfoBox
import com.mommydndn.app.ui.component.home.MarketListItemBox
import com.mommydndn.app.ui.component.home.ProfileSitterBox
import com.mommydndn.app.ui.component.home.SubBanner
import com.mommydndn.app.ui.theme.Grey50

@Composable
fun MainHomeScreen(
    navHostController: NavHostController
) {

    val ex: List<Banner> = listOf(
        Banner(
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg"
        ),
        Banner(
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg"
        ),
        Banner("https://example.com/banner3.jpg", "https://example.com/link3")
    )

    val ex1: List<SitterProfile> = listOf(
        SitterProfile(
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg",
            "John Doe",
            "25, Male",
            "Babysitter"
        ),
        SitterProfile(
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg",
            "Jane Smith",
            "30, Female",
            "Nanny"
        ),
        SitterProfile(
            "https://upload.wikimedia.org/wikipedia/commons/f/f9/Phoenicopterus_ruber_in_S%C3%A3o_Paulo_Zoo.jpg",
            "Alice Johnson",
            "22, Female",
            "Childminder"
        )
    )

    val ex2: List<JobOfferInfo> = listOf(
        JobOfferInfo("Babysitting", "New York", "$15/hr"),
        JobOfferInfo("Nanny", "Los Angeles", "$20/hr"),
        JobOfferInfo("Childminding", "Chicago", "$18/hr")
    )

    val ex3: List<CommunityPost> = listOf(
        CommunityPost(
            userProfileImgUrl = "https://example.com/user1.jpg",
            userName = "User 1",
            date = "2023-09-15",
            title = "첫 번째 게시물",
            content = "이것은 첫 번째 게시물의 내용입니다."
        ),
        CommunityPost(
            userProfileImgUrl = "https://example.com/user2.jpg",
            userName = "User 2",
            date = "2023-09-14",
            title = "두 번째 게시물",
            content = "이것은 두 번째 게시물의 내용입니다."
        ),
        CommunityPost(
            userProfileImgUrl = "https://example.com/user2.jpg",
            userName = "User 3",
            date = "2023-09-14",
            title = "세 번째 게시물",
            content = "이것은 세 번째 게시물의 내용입니다."
        ),
    )

    val ex4: List<MarketListItem> = listOf(
        MarketListItem(
            productImgUrl = "https://example.com/product1.jpg",
            price = "10,000원",
            productName = "상품 1",
            region = "서울",
            time = "2시간 전"
        ),
        MarketListItem(
            productImgUrl = "https://example.com/product2.jpg",
            price = "20,000원",
            productName = "상품 2",
            region = "부산",
            time = "1시간 전"
        ),
        MarketListItem(
            productImgUrl = "https://example.com/product2.jpg",
            price = "20,000원",
            productName = "상품 2",
            region = "부산",
            time = "1시간 전"
        ),
        MarketListItem(
            productImgUrl = "https://example.com/product2.jpg",
            price = "20,000원",
            productName = "상품 2",
            region = "부산",
            time = "1시간 전"
        ),
        MarketListItem(
            productImgUrl = "https://example.com/product2.jpg",
            price = "20,000원",
            productName = "상품 2",
            region = "부산",
            time = "1시간 전"
        ),
        MarketListItem(
            productImgUrl = "https://example.com/product2.jpg",
            price = "20,000원",
            productName = "상품 2",
            region = "부산",
            time = "1시간 전"
        ),
        MarketListItem(
            productImgUrl = "https://example.com/product2.jpg",
            price = "20,000원",
            productName = "상품 2",
            region = "부산",
            time = "1시간 전"
        ),
    )

    LazyColumn {
        item {
            BannerList(items = ex)
        }
        item {
            SubtextBox(size = SubtextBoxSize.L, titleText = "가장 가까운 시터님", rightText = "전체보기")
            LazyRow(
                modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 36.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(ex1) { item ->
                    ProfileSitterBox(item = item)
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )
        }
        item {
            SubtextBox(size = SubtextBoxSize.L, titleText = "도움이 필요한 주변 이웃", rightText = "더보기")
            LazyRow(
                modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 36.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(ex2) { item ->
                    JobOfferInfoBox(item = item)
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )
        }
        item {
            SubtextBox(size = SubtextBoxSize.L, titleText = "자유수다 추천글")
            Column(
                modifier = Modifier.padding(start = 32.dp, top = 28.dp, bottom = 36.dp),
            ) {
                ex3.forEach { item ->
                    CommunityPostBox(item = item)
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Grey50)
                    .padding(20.dp)
            )
        }
        item {
            SubtextBox(size = SubtextBoxSize.L, titleText = "집 앞 육아용품 장터")
            Column(
                modifier = Modifier.padding(
                    start = 24.dp,
                    top = 28.dp,
                    bottom = 24.dp,
                    end = 28.dp
                ),
            ) {
                Row {
                    MarketListItemBox(item = ex4[0])
                    Spacer(modifier = Modifier.fillMaxHeight().padding(12.dp))
                    MarketListItemBox(item = ex4[1])
                }
                Spacer(modifier = Modifier.fillMaxWidth().padding(24.dp))
                Row {
                    MarketListItemBox(item = ex4[2])
                    Spacer(modifier = Modifier.fillMaxHeight().padding(12.dp))
                    MarketListItemBox(item = ex4[3])
                }
                Spacer(modifier = Modifier.fillMaxWidth().padding(24.dp))
                Row {
                    MarketListItemBox(item = ex4[4])
                    Spacer(modifier = Modifier.fillMaxHeight().padding(12.dp))
                    MarketListItemBox(item = ex4[5])
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))
        }
        item {
            SubBanner()
            Footer() {}
        }
    }
}