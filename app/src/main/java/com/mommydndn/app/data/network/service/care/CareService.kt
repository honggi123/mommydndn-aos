package com.mommydndn.app.data.network.service.care

import com.mommydndn.app.data.network.service.care.request.CreateAgencyCareProviderRequest
import com.mommydndn.app.data.network.service.care.request.GetAgencyCareProviderListRequest
import com.mommydndn.app.data.network.service.care.request.CreateJobOpeningRequest
import com.mommydndn.app.data.network.service.care.request.GetJobOpeningListRequest
import com.mommydndn.app.data.network.service.care.request.CreateCareProviderRequest
import com.mommydndn.app.data.network.service.care.request.GetCareProviderListRequest
import com.mommydndn.app.data.network.service.care.response.CreateAgencyCareProviderResponse
import com.mommydndn.app.data.network.service.care.response.CreateJobOpeningResponse
import com.mommydndn.app.data.network.service.care.response.CreateCareProviderResponse
import com.mommydndn.app.data.network.service.care.response.GetAgencyCareProviderResponse
import com.mommydndn.app.data.network.service.care.response.GetAgencyCareProviderSummaryListResponse
import com.mommydndn.app.data.network.service.care.response.GetCareProviderResponse
import com.mommydndn.app.data.network.service.care.response.GetCareProviderSummaryListResponse
import com.mommydndn.app.data.network.service.care.response.GetCareTypeListResponse
import com.mommydndn.app.data.network.service.care.response.GetJobOpeningResponse
import com.mommydndn.app.data.network.service.care.response.GetJobOpeningSummaryListResponse
import com.mommydndn.app.data.network.service.care.response.GetMinimumWageResponse
import com.mommydndn.app.data.network.service.care.response.GetOtherAgenyConditionListResponse
import com.mommydndn.app.data.network.service.care.response.GetOtherIndividualConditionListResponse
import com.mommydndn.app.data.network.service.location.response.GetNearestCareProviderListResponse
import com.mommydndn.app.data.network.service.location.response.GetNearestJobOpeningListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun getNearestCareProviderList(): GetNearestCareProviderListResponse

    @GET("/api/caring/job-offer/nearest")
    suspend fun getNearestJobOpeningList(): GetNearestJobOpeningListResponse

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun getJobOpening(@Path("jobOfferId") id: Int): GetJobOpeningResponse

    @GET("/api/caring/job-seeker/{jobSeekerId}")
    suspend fun getCareProvider(@Path("jobSeekerId") id: Int): GetCareProviderResponse

    @GET("/api/caring/company/{companyId}")
    suspend fun getAgencyCareProvider(@Path("companyId") id: Int): GetAgencyCareProviderResponse

    @POST("/api/caring/job-offer/list")
    suspend fun getJobOpeningSummaryList(@Body request: GetJobOpeningListRequest): GetJobOpeningSummaryListResponse

    @POST("/api/caring/job-seeker/list")
    suspend fun getCareProviderSummaryList(@Body request: GetCareProviderListRequest): GetCareProviderSummaryListResponse

    @POST("/api/caring/company/list")
    suspend fun getAgencyCareProviderSummaryList(@Body request: GetAgencyCareProviderListRequest): GetAgencyCareProviderSummaryListResponse

    @GET("/api/caring/min-hourly-salary")
    suspend fun getMininumWage(): GetMinimumWageResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun getOtherIndividualConditions(): GetOtherIndividualConditionListResponse

    @GET("/api/caring/com-other-condition")
    suspend fun getOtherAgencyCondtions(): GetOtherAgenyConditionListResponse

    @GET("/api/caring/caring-type")
    suspend fun getCareTypes(): GetCareTypeListResponse

    @POST("/api/caring/job-offer")
    suspend fun createJobOpening(@Body request: CreateJobOpeningRequest): CreateJobOpeningResponse

    @POST("/api/caring/job-seeker")
    suspend fun createCareProvider(@Body request: CreateCareProviderRequest): CreateCareProviderResponse

    @POST("/api/caring/company")
    suspend fun createAgencyCareProvider(@Body request: CreateAgencyCareProviderRequest): CreateAgencyCareProviderResponse
}
