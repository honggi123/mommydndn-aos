package com.mommydndn.app.data.network.feature.babyitem

import com.mommydndn.app.data.api.model.response.GetBabyItemSummaryListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BabyItemService {
    @GET("/api/item/nearest")
    suspend fun fetchNearestBabyItemSummary(
        @Query("pageSize") size: Int,
        @Query("pageNum") page: Int,
        @Query("requestTimestamp") requestedAt: Long
    ): GetBabyItemSummaryListResponse
}