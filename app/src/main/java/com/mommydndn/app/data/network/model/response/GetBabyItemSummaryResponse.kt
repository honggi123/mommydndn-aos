package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.domain.model.care.BabyItem
import com.mommydndn.app.domain.model.care.BabyItemMeta
import com.mommydndn.app.domain.model.care.BabyItemsWithMeta
import kotlinx.serialization.Serializable

@Serializable
data class GetBabyItemSummaryResponse(
    val itemSummaryList: List<GetBabyItemResponse>,
    val meta: GetBabyItemMetaResponse
)

@Serializable
data class GetBabyItemResponse(
    val itemId: Int,
    val imageUrl: String,
    val price: Int,
    val title: String,
    val neighborhood: String,
    val createdAt: Long,
    val isLiked: Boolean
)

@Serializable
data class GetBabyItemMetaResponse(
    val totalCount: Int,
    val currentPageNum: Int,
    val requestTimestamp: Long
)



fun GetBabyItemResponse.toDomain(): BabyItem {
    return BabyItem(
        itemId = itemId,
        imageUrl = imageUrl,
        price = price,
        title = title,
        neighborhood = neighborhood,
        createdAt = createdAt,
        isLiked = isLiked
    )
}

fun GetBabyItemMetaResponse.toDomain(): BabyItemMeta {
    return BabyItemMeta(
        totalCount = totalCount,
        currentPageNum = currentPageNum,
        requestTimestamp = requestTimestamp
    )
}

fun GetBabyItemSummaryResponse.toDomain(): BabyItemsWithMeta {
    return BabyItemsWithMeta(
        itemSummaryList = itemSummaryList.map { it.toDomain() },
        meta = meta.toDomain()
    )
}