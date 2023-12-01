package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.model.response.BabyItemSummary
import com.mommydndn.app.data.network.service.BabyItemService
import com.mommydndn.app.domain.repository.BabyItemRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BabyItemDataRepository @Inject constructor(
    private val babyItemService: BabyItemService
) : BabyItemRepository {

    override fun fetchNearestBabyItemSummary(
        pageNum: Int, pageSize: Int, currentTimeStamp: Long
    ): Flow<BabyItemSummary> = flow {
        babyItemService.fetchNearestBabyItemSummary(
            pageSize = pageSize,
            pageNum = pageNum,
            requestTimestamp = currentTimeStamp
        ).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.Default)
}