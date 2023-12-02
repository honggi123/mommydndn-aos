package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.api.model.response.GetBabyItemSummaryResponse
import com.mommydndn.app.data.model.babyitem.BabyItemSummary
import kotlinx.coroutines.flow.Flow

interface BabyItemRepository {

    fun fetchNearestBabyItemSummary(
        pageNum: Int,
        pageSize: Int,
        currentTimestamp: Long
    ): Flow<BabyItemSummary>

}