package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun fetchUserNotificationSettings(): List<Notification>

}