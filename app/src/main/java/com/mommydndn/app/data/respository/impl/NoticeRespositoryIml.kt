package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.service.NoticeService
import com.mommydndn.app.data.api.service.TermsService
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.respository.NoticeRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticeRespositoryIml @Inject constructor(
    private val noticeService: NoticeService
) : NoticeRepository {
    override fun fetchUserNoticeSettings(): Flow<List<NoticeSetting>> = flow {
        noticeService.fetchUserNoticeSettings().suspendOnSuccess {
            val list = data.map {
                NoticeSetting(
                    isApproved = it.isApproved,
                    noticeTypeId = it.noticeTypeId,
                    noticeTypeName = it.noticeTypeName
                )
            }
            emit(list)
        }.onError { }
    }
}