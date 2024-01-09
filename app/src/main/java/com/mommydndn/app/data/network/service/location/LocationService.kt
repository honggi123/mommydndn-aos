package com.mommydndn.app.data.network.service.location

import com.mommydndn.app.data.network.service.location.response.GetSearchedNeighborhoodListResponse
import com.mommydndn.app.data.network.service.location.response.GetNearestNeighborhoodListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("/api/map/nearest")
    suspend fun getNearbyMyLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): GetNearestNeighborhoodListResponse

    @GET("/api/map/search")
    suspend fun getLocationsByKeyword(
        @Query("keyword") keyword: String,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("requestTimestamp") requestTimestamp: Long
    ): GetSearchedNeighborhoodListResponse
}
