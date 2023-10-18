package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.notice.NoticeSetting
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchUserNoticeSettings(): Flow<List<NoticeSetting>>

}