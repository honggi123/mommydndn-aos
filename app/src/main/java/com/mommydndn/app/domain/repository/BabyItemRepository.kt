package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.care.BabyItemsWithMeta
import kotlinx.coroutines.flow.Flow

interface BabyItemRepository {

    fun fetchNearestBabyItemSummary(
        pageNum: Int,
        pageSize: Int,
        currentTimestamp: Long
    ): Flow<BabyItemsWithMeta>

}