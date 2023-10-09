package com.mommydndn.app.data.respository.impl

import android.util.Log
import com.mommydndn.app.data.api.service.CommonService
import com.mommydndn.app.data.model.Banner
import com.mommydndn.app.data.model.SitterProfile
import com.mommydndn.app.data.respository.CommonRepositoy
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val commonService: CommonService
) : CommonRepositoy {
    override fun fetchBanners(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit
    ): Flow<List<Banner>> = flow {
        commonService.fetchBanners().suspendOnSuccess {
            val list = data.map {
                Banner(
                    bannerId = it.bannerId,
                    url = it.url ?: "",
                    targetUrl = it.targetUrl ?: ""
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