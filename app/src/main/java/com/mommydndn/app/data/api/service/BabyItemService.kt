package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.BabyItemSummary
import com.mommydndn.app.data.api.model.BabyItemSummaryResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BabyItemService {
    @GET("/api/item/nearest")
    suspend fun fetchNearestBabyItem(
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int,
    ): ApiResponse<BabyItemSummaryResponse>
}