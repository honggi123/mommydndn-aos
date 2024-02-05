package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.Notification

interface NotificationRepository {

    suspend fun getNotifications(): List<Notification>

    suspend fun updateNotificationsAllowed(notifications: List<Notification>)
}