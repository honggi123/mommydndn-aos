package com.mommydndn.app.data.model.babyitem

import com.mommydndn.app.data.api.model.response.GetBabyItemMetaResponse
import com.mommydndn.app.data.api.model.response.GetBabyItemResponse

data class BabyItemSummary(
    val itemSummaryList: List<BabyItem>,
    val meta: BabyItemMeta
)
data class BabyItem(
    val itemId: Int,
    val imageUrl: String,
    val price: Int,
    val title: String,
    val neighborhood: String,
    val createdAt: Long,
    val isLiked: Boolean
)

data class BabyItemMeta(
    val totalCount: Int,
    val currentPageNum: Int,
    val requestTimestamp: Long
)


