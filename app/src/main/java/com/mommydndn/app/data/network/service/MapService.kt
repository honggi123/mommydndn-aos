package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.response.LocationSearchResponse
import com.mommydndn.app.data.network.model.response.NearestResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapService {

    @GET("/api/map/nearest")
    suspend fun fetchNearestByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): ApiResponse<NearestResponse>

    @GET("/api/map/search")
    suspend fun fetchLocationsByKeyword(
        @Query("keyword") keyword: String,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("requestTimestamp") requestTimestamp: Long
    ): Response<LocationSearchResponse>
}