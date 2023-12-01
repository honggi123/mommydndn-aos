package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.response.BabyItemSummary
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BabyItemService {

    @GET("/api/item/nearest")
    suspend fun fetchNearestBabyItemSummary(
        @Query("pageSize") pageSize: Int,
        @Query("pageNum") pageNum: Int,
        @Query("requestTimestamp") requestTimestamp: Long
    ): ApiResponse<BabyItemSummary>
}