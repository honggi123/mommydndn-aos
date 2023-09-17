package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.LoginType
import com.mommydndn.app.data.model.NearestResponse
import com.skydoves.sandwich.ApiResponse

interface LocationRepository {
    suspend fun fetchNearest(latitude: Double, longitude: Double): ApiResponse<NearestResponse>

}