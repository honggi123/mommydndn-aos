package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.EmdItem
import com.mommydndn.app.data.model.LocationInfo
import com.mommydndn.app.data.model.NoticeSetting
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchUserNoticeSettings(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<NoticeSetting>>

}