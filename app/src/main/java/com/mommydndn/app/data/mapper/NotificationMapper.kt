package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNotificationSetting
import com.mommydndn.app.domain.model.Notification
import com.mommydndn.app.domain.model.NotificationType

internal fun List<NetworkNotificationSetting>.toNotifications(): List<Notification> = map {
    Notification(
        type = NotificationType(it.id.toString(), it.name),
        isAllowed = it.isAllowed
    )
}
