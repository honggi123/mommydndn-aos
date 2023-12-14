package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.response.toDomain
import com.mommydndn.app.data.api.service.BabyItemService
import com.mommydndn.app.domain.model.care.BabyItemsWithMeta
import com.mommydndn.app.domain.repository.BabyItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BabyItemDataRepository @Inject constructor(
    private val babyItemService: BabyItemService
) : BabyItemRepository {

    override fun fetchNearestBabyItemSummary(
        pageNum: Int,
        pageSize: Int,
        currentTimeStamp: Long
    ): Flow<BabyItemsWithMeta> = flow {
        emit(
            babyItemService.fetchNearestBabyItemSummary(
                pageSize = pageSize,
                pageNum = pageNum,
                requestTimestamp = currentTimeStamp
            ).toDomain()
        )
    }.flowOn(Dispatchers.IO)

}