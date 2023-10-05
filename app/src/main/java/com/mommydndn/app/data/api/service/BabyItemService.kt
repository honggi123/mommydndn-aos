package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.NearestBabyItemResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface BabyItemService {
    @GET("/api/item/nearest")
    suspend fun fetchNearestBabyItem(): ApiResponse<List<NearestBabyItemResponse>>
}