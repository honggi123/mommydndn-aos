package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.location.response.GetSearchLocationsResponse
import com.mommydndn.app.data.network.model.location.response.GetNearestNeighborhoodListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("/api/map/nearest")
    suspend fun getNearestByMyLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<GetNearestNeighborhoodListResponse>

    @GET("/api/map/search")
    suspend fun getLocationsByKeyword(
        @Query("keyword") keyword: String,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("requestTimestamp") requestTimestamp: Long
    ): Response<GetSearchLocationsResponse>
}
