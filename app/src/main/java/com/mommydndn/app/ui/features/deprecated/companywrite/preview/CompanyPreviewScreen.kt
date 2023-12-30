package com.mommydndn.app.ui.features.care.company.write.preview

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mommydndn.app.data.model.care.CompanyPreview

@Composable
fun CompanyPreviewScreen(
    companyPreiew: CompanyPreview?,
    navController: NavHostController,
    // viewModel: CompanyPrevieViewModel = hiltViewModel()
) {
    /*
val authorInfo by viewModel.authorInfo.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(White)
    ) {

        Header(leftContent = {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow_left),
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                BannerList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    items = companyPreiew?.coverImageList?.map {
                        Banner(bannerId = 0, url = it, targetUrl = "")
                    })


                val profilePainter = rememberImagePainter(
                    data = companyPreiew?.profileImage,
                    builder = {
                        crossfade(true)
                    }
                )

                Image(
                    painter = profilePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 30.dp, y = 154.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Grey300),
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 36.dp,
                    bottom = 12.dp
                )
            ) {
                EnterpriseListItem(
                    modifier = Modifier.fillMaxWidth(), profileBoxType = ProfileBoxType.DETAIL,
                    nickname = authorInfo?.nickname ?: "",
                    neighborhood = "${authorInfo?.emd?.name}외 24개 동네",
                    isDndnAuthenticated = authorInfo?.isDnDnAuthenticated ?: false,
                    matchingCount = authorInfo?.matchingCount ?: 0,
                    reviewCount = authorInfo?.reviewCount ?: 0,
                    responseRate = authorInfo?.responseRate ?: "",
                )
            }

            SmallCustomTab(tabs = listOf("소개", "돌봄분야", "받은 후기"))

            Box(
                modifier = Modifier.padding(24.dp)
            ) {
                UserIntroductionBox(
                    modifier = Modifier.fillMaxWidth(),
                    title = "${authorInfo?.nickname}의 한마디",
                    content = companyPreiew?.introduce ?: ""
                )
            }

            SubtextBox(
                titleText = "${authorInfo?.nickname}에 대하여"
            )

            val salaryText =
                "평균 ${companyPreiew?.startSalary}만원 ~ ${companyPreiew?.endSalary}만원 (수수료 ${companyPreiew?.commission}%)"


            MediumProfileInfoStack(
                modifier = Modifier.fillMaxWidth(),
                certificationList = emptyList(),
                infos = companyPreiew?.etcCheckedList?.map { it.displayName } ?: emptyList(),
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
                    if (companyPreiew != null) {
                        items(companyPreiew.caringTypeList) { item ->
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
                        if (companyPreiew != null) {
                            viewModel.createCompany(companyPreiew, navController, context)
                        }
                    }
                )
            }
        }
    }
     */

}