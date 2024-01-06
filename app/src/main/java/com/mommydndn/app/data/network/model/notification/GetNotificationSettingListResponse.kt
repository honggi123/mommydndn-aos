package com.mommydndn.app.data.api.model.response

import com.mommydndn.app.data.model.notification.Notification
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNotificationSettingListResponse = List<NotificationSettingResponse>

@Serializable
@SerialName("NoticeSettingResponse")
data class NotificationSettingResponse(
    val isApproved: Boolean,
    val noticeTypeId: Int,
    val noticeTypeName: String
)

fun GetNotificationSettingListResponse.toDomain(): List<Notification> {
    return this.map {
        Notification(
            isApproved = it.isApproved,
            noticeTypeId = it.noticeTypeId,
            noticeTypeName = it.noticeTypeName,
            isSelected = false
        )
    }
}