package com.mommydndn.app.data.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BabyItem(
    val itemId: Int,
    val imageUrl: String,
    val price: Int,
    val title: String,
    val neighborhood: String,
    val createdAt: Long,
    val isLiked: Boolean
)

@Serializable
data class BabyItemSummary(
    val itemSummaryList: List<BabyItem>,
    val meta: BabyItemMeta
)

@Serializable
data class BabyItemMeta(
    val totalCount: Int,
    val currentPageNum: Int,
    val requestTimestamp: Long
)