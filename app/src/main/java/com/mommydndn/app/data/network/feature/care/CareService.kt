package com.mommydndn.app.data.network.feature.care

import com.mommydndn.app.data.network.feature.care.request.CreateAgencyCareProviderRequest
import com.mommydndn.app.data.network.feature.care.request.GetAgencyCareProviderListRequest
import com.mommydndn.app.data.network.feature.care.request.CreateJobOpeningRequest
import com.mommydndn.app.data.network.feature.care.request.GetJobOpeningListRequest
import com.mommydndn.app.data.network.feature.care.request.CreateCareProviderRequest
import com.mommydndn.app.data.network.feature.care.request.GetCareProviderListRequest
import com.mommydndn.app.data.network.feature.care.response.CreateAgencyCareProviderResponse
import com.mommydndn.app.data.network.feature.care.response.CreateJobOpeningResponse
import com.mommydndn.app.data.network.feature.care.response.CreateCareProviderResponse
import com.mommydndn.app.data.network.feature.care.response.GetAgencyCareProviderResponse
import com.mommydndn.app.data.network.feature.care.response.GetAgencyCareProviderSummaryListResponse
import com.mommydndn.app.data.network.feature.care.response.GetCareProviderResponse
import com.mommydndn.app.data.network.feature.care.response.GetCareProviderSummaryListResponse
import com.mommydndn.app.data.network.feature.care.response.GetCareTypeListResponse
import com.mommydndn.app.data.network.feature.care.response.GetJobOpeningResponse
import com.mommydndn.app.data.network.feature.care.response.GetJobOpeningSummaryListResponse
import com.mommydndn.app.data.network.feature.care.response.GetMinimumWageResponse
import com.mommydndn.app.data.network.feature.care.response.GetOtherAgenyConditionListResponse
import com.mommydndn.app.data.network.feature.care.response.GetOtherIndividualConditionListResponse
import com.mommydndn.app.data.network.feature.location.response.GetNearestCareProviderListResponse
import com.mommydndn.app.data.network.feature.location.response.GetNearestJobOpeningListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestCareProviderList(): GetNearestCareProviderListResponse

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOpeningList(): GetNearestJobOpeningListResponse

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun fetchJobOpening(@Path("jobOfferId") id: Int): GetJobOpeningResponse

    @GET("/api/caring/job-seeker/{jobSeekerId}")
    suspend fun fetchCareProvider(@Path("jobSeekerId") id: Int): GetCareProviderResponse

    @GET("/api/caring/company/{companyId}")
    suspend fun fetchAgencyCareProvider(@Path("companyId") id: Int): GetAgencyCareProviderResponse

    @POST("/api/caring/job-offer/list")
    suspend fun fetchJobOpeningSummaryList(@Body request: GetJobOpeningListRequest): Response<GetJobOpeningSummaryListResponse>

    @POST("/api/caring/job-seeker/list")
    suspend fun fetchCareProviderSummaryList(@Body request: GetCareProviderListRequest): Response<GetCareProviderSummaryListResponse>

    @POST("/api/caring/company/list")
    suspend fun fetchAgencyCareProviderSummaryList(@Body request: GetAgencyCareProviderListRequest): Response<GetAgencyCareProviderSummaryListResponse>

    @GET("/api/caring/min-hourly-salary")
    suspend fun fetchMininumWage(): GetMinimumWageResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun fetchOtherIndividualConditions(): GetOtherIndividualConditionListResponse

    @GET("/api/caring/com-other-condition")
    suspend fun fetchOtherAgencyCondtions(): GetOtherAgenyConditionListResponse

    @GET("/api/caring/caring-type")
    suspend fun fetchCareTypes(): GetCareTypeListResponse

    @POST("/api/caring/job-offer")
    suspend fun createJobOpening(@Body request: CreateJobOpeningRequest): CreateJobOpeningResponse

    @POST("/api/caring/job-seeker")
    suspend fun createCareProvider(@Body request: CreateCareProviderRequest): CreateCareProviderResponse

    @POST("/api/caring/company")
    suspend fun createAgencyCareProvider(@Body request: CreateAgencyCareProviderRequest): CreateAgencyCareProviderResponse
}
