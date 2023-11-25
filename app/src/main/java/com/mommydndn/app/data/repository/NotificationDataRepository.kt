package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.service.NoticeService
import com.mommydndn.app.data.model.notice.Notification
import com.mommydndn.app.domain.repository.NoticeRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotificationDataRepository @Inject constructor(
    private val noticeService: NoticeService
) : NoticeRepository {
    override fun fetchUserNoticeSettings(): Flow<List<Notification>> = flow {
        noticeService.fetchUserNoticeSettings().suspendOnSuccess {
            val list = data.map {
                Notification(
                    isApproved = it.isApproved,
                    noticeTypeId = it.noticeTypeId,
                    noticeTypeName = it.noticeTypeName,
                    isSelected = false
                )
            }
            emit(list)
        }
    }.flowOn(Dispatchers.IO)
}