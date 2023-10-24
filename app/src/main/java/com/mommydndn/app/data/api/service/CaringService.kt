package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.JobOfferRequest
import com.mommydndn.app.data.api.model.response.CaringTypeResponse
import com.mommydndn.app.data.api.model.response.CompanyEtcCheckItem
import com.mommydndn.app.data.api.model.response.IndividualEtcCheckItem
import com.mommydndn.app.data.api.model.response.NearestJobOfferResponse
import com.mommydndn.app.data.model.care.JobOfferSummary
import com.mommydndn.app.data.model.care.JobSeeker
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface CaringService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestJobSeeker(): ApiResponse<List<JobSeeker>>

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOffer(): ApiResponse<List<NearestJobOfferResponse>>

    @GET("/api/caring/ind-other-condition")
    suspend fun fetchIndividualEtcCheckList(): ApiResponse<List<IndividualEtcCheckItem>>

    @GET("/api/caring/com-other-condition")
    suspend fun fetchCompanyEtcCheckList(): ApiResponse<List<CompanyEtcCheckItem>>

    @GET("/api/caring/caring-type")
    suspend fun fetchCaringTypesResponse(): ApiResponse<List<CaringTypeResponse>>

    @GET("/api/caring/min-hourly-salary")
    suspend fun fetchMinHourlySalary(): ApiResponse<MinHourlySalary>

    @GET("/api/caring/job-offer")
    suspend fun craeteJobOffer(
        @Body jobOfferRequest: JobOfferRequest
    ): ApiResponse<Unit>

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