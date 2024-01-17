package com.mommydndn.app.domain.model

data class Notification(
    val type: NotificationType,
    val isAllowed: Boolean,
)

data class NotificationType(
    val id: String,
    val name: String,
)