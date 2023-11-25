package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.model.notice.Notification
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchUserNoticeSettings(): Flow<List<Notification>>

}