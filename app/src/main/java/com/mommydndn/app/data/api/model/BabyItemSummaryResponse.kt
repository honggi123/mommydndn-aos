package com.mommydndn.app.data.api.model

import com.google.gson.annotations.SerializedName

data class BabyItemSummary(
    @SerializedName("itemId")
    val itemId: Int,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("neighborhood")
    val neighborhood: String,

    @SerializedName("createdAt")
    val createdAt: Long,

    @SerializedName("isLiked")
    val isLiked: Boolean
)

data class BabyItemSummaryResponse(
    @SerializedName("itemSummaryList")
    val itemSummaryList: List<BabyItemSummary>,

    @SerializedName("meta")
    val meta: Meta
)

data class Meta(
    @SerializedName("totalCount")
    val totalCount: Int,

    @SerializedName("currentPageNum")
    val currentPageNum: Int
)