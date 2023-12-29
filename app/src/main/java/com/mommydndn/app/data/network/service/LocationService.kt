package com.mommydndn.app.data.network.service

<<<<<<<< HEAD:app/src/main/java/com/mommydndn/app/data/network/service/LocationService.kt
import com.mommydndn.app.data.api.model.response.GetLocationResponse
import com.mommydndn.app.data.api.model.response.GetNearestResponse
========
import com.mommydndn.app.data.network.model.response.LocationSearchResponse
import com.mommydndn.app.data.network.model.response.NearestResponse
import com.skydoves.sandwich.ApiResponse
>>>>>>>> refactor/code_care:app/src/main/java/com/mommydndn/app/data/network/service/MapService.kt
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("/api/map/nearest")
    suspend fun fetchNearestByMyLocation(
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
