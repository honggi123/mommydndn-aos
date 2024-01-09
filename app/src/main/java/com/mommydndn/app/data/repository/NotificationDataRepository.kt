package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.notification.NotificationService
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.data.network.service.common.response.GetBannerListResponse
import com.mommydndn.app.data.network.service.user.UserService
import com.mommydndn.app.domain.model.banner.Banner
import com.mommydndn.app.domain.repository.NotificationRepository

class NotificationDataRepository constructor(
    private val service: UserService
) : NotificationRepository {

    override suspend fun fetchUserNotificationSettings(): List<Notification> {
//       return service.fetchNotificationSettings().toDomain()
        return emptyList()
    }
}

