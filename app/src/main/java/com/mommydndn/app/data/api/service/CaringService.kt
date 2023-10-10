package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.NearestJobOfferResponse
import com.mommydndn.app.data.api.model.NearestResponse
import com.mommydndn.app.data.model.JobOfferSummary
import com.mommydndn.app.data.model.JobSeeker
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CaringService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestJobSeeker(): ApiResponse<List<JobSeeker>>

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOffer(): ApiResponse<List<NearestJobOfferResponse>>

    @GET("/api/map/search")
    suspend fun fetchJobOfferSummary(
        @Query("keyword") keyword: String,
        @Query("pageNum") pageNum: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortingCondition") sortingCondition: String,
        @Query("emdId") emdId: Int,
        @Query("neighborhoodScope") neighborhoodScope: String,
        @Query("caringTypeCodeList") caringTypeCodeList: List<String>,
        @Query("minHourlySalary") minHourlySalary: Int,
        @Query("maxHourlySalary") maxHourlySalary: Int,
        @Query("dayTypeCodeList") dayTypeCodeList: List<String>,
        @Query("startTime") startTime: String,
        @Query("endTime") endTime: String,
        @Query("taskTypeCodeList") taskTypeCodeList: List<String>
    ): Response<List<JobOfferSummary>>
}