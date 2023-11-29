package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.response.GetLocationResponse
import com.mommydndn.app.data.api.model.response.GetNearestResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("/api/map/nearest")
    suspend fun fetchNearestByLocation(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20
    ): Response<GetNearestResponse>

    @GET("/api/map/search")
    suspend fun fetchLocationsByKeyword(
        @Query("keyword") keyword: String,
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("requestTimestamp") requestTimestamp: Long
    ): Response<GetLocationResponse>
}