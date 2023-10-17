package com.mommydndn.app.data.api.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("currentPageNum")
    val currentPageNum: Int,
    @SerialName("totalCount")
    val totalCount: Int
)