package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.mommydndn.app.data.api.service.NoticeService
import com.mommydndn.app.data.api.service.TermsService
import com.mommydndn.app.data.model.NoticeSetting
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.respository.NoticeRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class NoticeRespositoryIml @Inject constructor(
    private val noticeService: NoticeService
) : NoticeRepository {
    override fun fetchUserNoticeSettings(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<NoticeSetting>> = flow {
        noticeService.fetchUserNoticeSettings().suspendOnSuccess {
            val list = data.map {
                NoticeSetting(
                    isApproved = it.isApproved,
                    noticeTypeId = it.noticeTypeId,
                    noticeTypeName = it.noticeTypeName,
                    isSelected = false
                )
            }
            emit(list)
        }.onError {
            onError("code: $statusCode, errorBody: $errorBody")
        }.onException {
            onError(message)
        }
    }.onCompletion {
        onComplete()
    }.flowOn(Dispatchers.IO)
}