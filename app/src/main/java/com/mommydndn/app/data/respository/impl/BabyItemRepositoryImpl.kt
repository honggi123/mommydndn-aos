package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.model.BabyItemSummary
import com.mommydndn.app.data.api.service.BabyItemService
import com.mommydndn.app.data.respository.BabyItemRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BabyItemRepositoryImpl @Inject constructor(
    private val babyItemService: BabyItemService
) : BabyItemRepository {
    override fun fetchNearestBabyItemSummary(
        pageNum: Int, pageSize: Int
    ): Flow<BabyItemSummary> = flow {
        babyItemService.fetchNearestBabyItemSummary(
            pageSize = pageSize,
            pageNum = pageNum
        ).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.Default)




}