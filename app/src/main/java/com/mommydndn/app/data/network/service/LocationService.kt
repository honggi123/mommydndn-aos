package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.service.response.GetNearestNeighborhoodsResponse
import com.mommydndn.app.data.network.service.response.SearchLocationByKeywordResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("/api/map/nearest")
    suspend fun getNearestNeighborhoods(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): GetNearestNeighborhoodsResponse

    @GET("/api/map/search")
    suspend fun searchLocationByKeyword(
        @Query("keyword") keyword: String,
        @Query("skip") page: Int = 0,
        @Query("limit") size: Int = 20,
        @Query("requestTimestamp") requestedAt: Long = System.currentTimeMillis()
    ): SearchLocationByKeywordResponse
}
