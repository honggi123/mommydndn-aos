package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.NearestResponse
import com.skydoves.sandwich.ApiResponse

interface LocationRepository {
    suspend fun fetchNearestByLocation(latitude: Double, longitude: Double): ApiResponse<NearestResponse>

    suspend fun fetchNearestByKeyword(keyword: String): ApiResponse<NearestResponse>

}