package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.service.NoticeService
import com.mommydndn.app.data.model.notification.Notification
import com.mommydndn.app.domain.repository.NotificationRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotificationDataRepository @Inject constructor(
    private val noticeService: NoticeService
) : NotificationRepository {
    override suspend fun fetchUserNotificationSettings(): List<Notification> {
       return noticeService.fetchUserNoticeSettings().map {
                Notification(
                    isApproved = it.isApproved,
                    noticeTypeId = it.noticeTypeId,
                    noticeTypeName = it.noticeTypeName,
                    isSelected = false
                )
            }

    }
}