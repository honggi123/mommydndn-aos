package com.mommydndn.app.data.model.notification

data class Notification(
    val isApproved: Boolean,
    val noticeTypeId: Int,
    val noticeTypeName: String,
    val isSelected: Boolean
)
