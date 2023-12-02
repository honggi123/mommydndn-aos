package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.GetBabyItemSummaryResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BabyItemService {

    @GET("/api/item/nearest")
    suspend fun fetchNearestBabyItemSummary(
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int,
        @Query("requestTimestamp") requestTimestamp: Long
    ): GetBabyItemSummaryResponse
}