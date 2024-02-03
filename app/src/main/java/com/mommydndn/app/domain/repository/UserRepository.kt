package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.Notification

interface UserRepository {

    suspend fun updateNotificationsAllowed(notifications: List<Notification>)
}