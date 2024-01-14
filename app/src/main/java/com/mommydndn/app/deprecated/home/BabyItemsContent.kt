package com.mommydndn.app.deprecated.home

//@Composable
//fun BabyItemsContent(
//    babyItemUiState: HomeBabyItemUiState,
//    loadNextPage: (Int) -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    val babyItems = babyItemUiState.babyItems
//
//    if (babyItems.isEmpty()) {
//        return
//    }
//
//    Column(
//        modifier = modifier.fillMaxWidth()
//    ) {
//
//        SubtextBox(
//            modifier = Modifier.fillMaxWidth(),
//            size = SubtextBoxSize.L,
//            titleText = stringResource(id = R.string.category_baby_items_title)
//        )
//
//        Box(modifier = modifier.fillMaxWidth()) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        horizontal = 24.dp, vertical = 28.dp
//                    ),
//            ) {
//                babyItems.chunked(2).forEach { rowItems ->
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(12.dp)
//                    ) {
//                        rowItems.forEach { item ->
//                            MarketListItemBox(modifier = Modifier.weight(1f), item = item)
//                        }
//                    }
//
//                    Spacer(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(24.dp)
//                    )
//                }
//            }
//        }
//
//        when (babyItemUiState) {
//            is HomeBabyItemUiState.Success -> {
//
//                // 1.현재 페이지 <= MAX_MORE_BABY_ITEM_PAGE
//                // 2.현재까지 아이템 총 개수에 추가 되어야하는 아이템 개수를 더했을 때 보다 다음 페이지까지의 총 개수가 더 적을 경우
//                //  1 page -> 0 + 추가 되어야하는 아이템 개수 < 아이템의 총 개수
//                //  2 page -> (1 * 추가 되어야하는 아이템 개수) + 추가 되어야하는 아이템 개수 < 아이템의 총 개수
//
//                val shouldShowLoadMoreButton =
//                    babyItemUiState.babyItemsPagingMeta.currentPageNum <= MAX_BABY_ITEM_PAGES
//                            && ((babyItemUiState.babyItemsPagingMeta.currentPageNum - 1) * MORE_BABY_ITEM_SIZE) + MORE_BABY_ITEM_SIZE < babyItemUiState.babyItemsPagingMeta.totalCount
//
//                if (shouldShowLoadMoreButton) {
//                    Button(
//                        modifier = Modifier
//                            .border(width = 1.dp, color = Color(0xFFF0F2F4))
//                            .fillMaxWidth(),
//                        onClick = {
//                            loadNextPage(babyItemUiState.babyItemsPagingMeta.currentPageNum)
//                        }
//                    ) {
//                        Text(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 20.dp, bottom = 20.dp),
//                            text = stringResource(id = R.string.see_more),
//                            style = MaterialTheme.typography.paragraph300.copy(
//                                fontWeight = FontWeight.Normal,
//                                color = Salmon600
//                            ),
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                }
//            }
//
//            is HomeBabyItemUiState.Loading -> {
//                // TODO
//            }
//        }
//
//    }
//}