package com.mommydndn.app.data.mapper

import com.mommydndn.app.data.network.model.NetworkNotificationSetting
import com.mommydndn.app.domain.model.Notification
import com.mommydndn.app.domain.model.NotificationType

internal fun List<NetworkNotificationSetting>.toDomain(): List<Notification> = map {
    Notification(
        type = mapToNotificationType(it.id.toString(), it.name),
        isAllowed = it.isAllowed
    )
}

internal fun mapToNotificationType(id: String, name: String): NotificationType =
    NotificationType(id, name)