package com.mommydndn.app.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkNotificationSetting(
    @SerialName("noticeTypeId")
    val id: Long,
    @SerialName("noticeTypeName")
    val name: String,
    @SerialName("isApproved")
    val isAllowed: Boolean,
)