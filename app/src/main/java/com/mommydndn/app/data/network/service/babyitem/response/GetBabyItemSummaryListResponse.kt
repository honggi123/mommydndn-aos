package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.domain.model.care.BabyItem
import com.mommydndn.app.domain.model.care.BabyItemMeta
import com.mommydndn.app.domain.model.care.BabyItemsWithMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBabyItemSummaryListResponse(
    @SerialName("itemSummaryList")
    val items: List<BabyItemResponse>,
    val meta: BabyItemMetaResponse
)

@Serializable
data class BabyItemResponse(
    @SerialName("itemId")
    val id: Int,
    val imageUrl: String,
    val price: Int,
    val title: String,
    val neighborhood: String,
    val createdAt: Long,
    val isLiked: Boolean
)

@Serializable
data class BabyItemMetaResponse(
    val totalCount: Int,
    val currentPageNum: Int,
    val requestTimestamp: Long
)

fun BabyItemResponse.toDomain(): BabyItem {
    return BabyItem(
        itemId = id,
        imageUrl = imageUrl,
        price = price,
        title = title,
        neighborhood = neighborhood,
        createdAt = createdAt,
        isLiked = isLiked
    )
}

fun BabyItemMetaResponse.toDomain(): BabyItemMeta {
    return BabyItemMeta(
        totalCount = totalCount,
        currentPageNum = currentPageNum,
        requestTimestamp = requestTimestamp
    )
}

fun GetBabyItemSummaryListResponse.toDomain(): BabyItemsWithMeta {
    return BabyItemsWithMeta(
        itemSummaryList = items.map { it.toDomain() },
        meta = meta.toDomain()
    )
}