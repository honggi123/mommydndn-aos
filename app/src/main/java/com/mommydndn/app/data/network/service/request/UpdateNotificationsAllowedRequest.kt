package com.mommydndn.app.data.network.service.request

import kotlinx.serialization.SerialName

typealias UpdateNotificationsAllowedRequest = List<UpdateNotificationAllowed>

data class UpdateNotificationAllowed(
    @SerialName("noticeTypeId")
    val id: Int,
    @SerialName("isApproved")
    val isAllowed: Boolean
)
