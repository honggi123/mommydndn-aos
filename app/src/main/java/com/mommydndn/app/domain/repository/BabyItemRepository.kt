package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.network.model.response.BabyItemSummary
import kotlinx.coroutines.flow.Flow

interface BabyItemRepository {
    fun fetchNearestBabyItemSummary(
        pageNum: Int, pageSize: Int, currentTimestamp: Long
    ): Flow<BabyItemSummary>

}