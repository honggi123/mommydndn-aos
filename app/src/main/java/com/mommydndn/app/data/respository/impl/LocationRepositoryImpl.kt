package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.model.NearestResponse
import com.mommydndn.app.data.respository.LocationRepository
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LocationRepository {
    override suspend fun fetchNearest(
        latitude: Double,
        longitude: Double
    ): ApiResponse<NearestResponse> =
        apiService.fetchNearest(latitude = latitude, longitude = longitude)

}