package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.care.request.CreateAgencyCareProviderRequest
import com.mommydndn.app.data.network.model.care.request.GetAgencyCareProviderListRequest
import com.mommydndn.app.data.network.model.care.request.CreateJobOpeningRequest
import com.mommydndn.app.data.network.model.care.request.GetJobOpeningListRequest
import com.mommydndn.app.data.network.model.care.request.CreateCareProviderRequest
import com.mommydndn.app.data.network.model.care.request.GetCareProviderListRequest
import com.mommydndn.app.data.network.model.care.response.CreateAgencyCareProviderResponse
import com.mommydndn.app.data.network.model.care.response.CreateJobOpeningResponse
import com.mommydndn.app.data.network.model.care.response.CreateCareProviderResponse
import com.mommydndn.app.data.network.model.care.response.GetAgencyCareProviderSummaryListResponse
import com.mommydndn.app.data.network.model.care.response.GetCareProviderSummaryListResponse
import com.mommydndn.app.data.network.model.care.response.GetCareTypeListResponse
import com.mommydndn.app.data.network.model.care.response.GetJobOpeningResponse
import com.mommydndn.app.data.network.model.care.response.GetJobOpeningSummaryListResponse
import com.mommydndn.app.data.network.model.care.response.GetMinimumWageResponse
import com.mommydndn.app.data.network.model.care.response.GetOtherAgenyConditionListResponse
import com.mommydndn.app.data.network.model.care.response.GetOtherIndividualConditionListResponse
import com.mommydndn.app.data.network.model.location.response.GetNearestJobOpeningListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareService {

    @POST("/api/caring/job-offer")
    suspend fun createJobOpening(@Body request: CreateJobOpeningRequest): CreateJobOpeningResponse

    @POST("/api/caring/job-seeker")
    suspend fun createCareProvider(@Body request: CreateCareProviderRequest): CreateCareProviderResponse

    @POST("/api/caring/company")
    suspend fun createAgencyCareProvider(@Body request: CreateAgencyCareProviderRequest): CreateAgencyCareProviderResponse

    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestCareProvider(): Unit // TODO

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOpening(): GetNearestJobOpeningListResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun fetchOtherIndividualCheckList(): GetOtherIndividualConditionListResponse

    @GET("/api/caring/com-other-condition")
    suspend fun fetchOtherAgencyCheckList(): GetOtherAgenyConditionListResponse

    @GET("/api/caring/caring-type")
    suspend fun fetchCaringTypesResponse(): GetCareTypeListResponse

    @GET("/api/caring/min-hourly-salary")
    suspend fun fetchMinHourlySalary(): GetMinimumWageResponse

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun fetchJobOpening(@Path("jobOfferId") id: Int): GetJobOpeningResponse

    @POST("/api/caring/job-offer/list")
    suspend fun fetchJobOpeningSummaryList(@Body request: GetJobOpeningListRequest): Response<GetJobOpeningSummaryListResponse>

    @POST("/api/caring/job-seeker/list")
    suspend fun fetchCareProviderSummaryList(@Body request: GetCareProviderListRequest): Response<GetCareProviderSummaryListResponse>

    @POST("/api/caring/company/list")
    suspend fun fetchAgencyCareProviderSummaryList(@Body request: GetAgencyCareProviderListRequest): Response<GetAgencyCareProviderSummaryListResponse>
}
