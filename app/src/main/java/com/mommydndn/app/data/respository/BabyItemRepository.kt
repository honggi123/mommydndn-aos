package com.mommydndn.app.data.respository

import com.mommydndn.app.data.api.model.response.BabyItemSummary
import kotlinx.coroutines.flow.Flow

interface BabyItemRepository {
    fun fetchNearestBabyItemSummary(
        pageNum: Int, pageSize: Int, currentTimestamp: Long
    ): Flow<BabyItemSummary>

}