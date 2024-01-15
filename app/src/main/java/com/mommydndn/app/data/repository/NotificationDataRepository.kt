package com.mommydndn.app.data.repository

import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.data.network.service.UserService
import com.mommydndn.app.domain.repository.NotificationRepository

class NotificationDataRepository constructor(
    private val service: UserService
) : NotificationRepository {

    override suspend fun fetchUserNotificationSettings(): List<Notification> {
//       return service.fetchNotificationSettings().toDomain()
        return emptyList()
    }
}

