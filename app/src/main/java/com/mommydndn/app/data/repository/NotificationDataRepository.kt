package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.response.toDomain
import com.mommydndn.app.data.network.feature.notification.NotificationService
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.domain.repository.NotificationRepository

class NotificationDataRepository constructor(
    private val notificationService: NotificationService
) : NotificationRepository {

    override suspend fun fetchUserNotificationSettings(): List<Notification> =
        notificationService.fetchUserNotificationSettings().toDomain()

}