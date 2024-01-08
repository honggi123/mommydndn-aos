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
    suspend fun fetchJobOpening(@Path("jobOfferId") id: Int): com.mommydndn.app.data.network.feature.care.response.GetJobOpeningResponse

    @GET("/api/caring/job-seeker/{jobSeekerId}")
    suspend fun fetchCareProvider(@Path("jobSeekerId") id: Int): com.mommydndn.app.data.network.feature.care.response.GetCareProviderResponse

    @GET("/api/caring/company/{companyId}")
    suspend fun fetchAgencyCareProvider(@Path("companyId") id: Int): com.mommydndn.app.data.network.feature.care.response.GetAgencyCareProviderResponse

    @POST("/api/caring/job-offer/list")
    suspend fun fetchJobOpeningSummaryList(@Body request: com.mommydndn.app.data.network.feature.care.request.GetJobOpeningListRequest): Response<com.mommydndn.app.data.network.feature.care.response.GetJobOpeningSummaryListResponse>

    @POST("/api/caring/job-seeker/list")
    suspend fun fetchCareProviderSummaryList(@Body request: com.mommydndn.app.data.network.feature.care.request.GetCareProviderListRequest): Response<com.mommydndn.app.data.network.feature.care.response.GetCareProviderSummaryListResponse>

    @POST("/api/caring/company/list")
    suspend fun fetchAgencyCareProviderSummaryList(@Body request: com.mommydndn.app.data.network.feature.care.request.GetAgencyCareProviderListRequest): Response<com.mommydndn.app.data.network.feature.care.response.GetAgencyCareProviderSummaryListResponse>

    @GET("/api/caring/min-hourly-salary")
    suspend fun fetchMininumWage(): com.mommydndn.app.data.network.feature.care.response.GetMinimumWageResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun fetchOtherIndividualConditions(): com.mommydndn.app.data.network.feature.care.response.GetOtherIndividualConditionListResponse

    @GET("/api/caring/com-other-condition")
    suspend fun fetchOtherAgencyCondtions(): com.mommydndn.app.data.network.feature.care.response.GetOtherAgenyConditionListResponse

    @GET("/api/caring/caring-type")
    suspend fun fetchCareTypes(): com.mommydndn.app.data.network.feature.care.response.GetCareTypeListResponse

    @POST("/api/caring/job-offer")
    suspend fun createJobOpening(@Body request: com.mommydndn.app.data.network.feature.care.request.CreateJobOpeningRequest): com.mommydndn.app.data.network.feature.care.response.CreateJobOpeningResponse

    @POST("/api/caring/job-seeker")
    suspend fun createCareProvider(@Body request: com.mommydndn.app.data.network.feature.care.request.CreateCareProviderRequest): com.mommydndn.app.data.network.feature.care.response.CreateCareProviderResponse

    @POST("/api/caring/company")
    suspend fun createAgencyCareProvider(@Body request: com.mommydndn.app.data.network.feature.care.request.CreateAgencyCareProviderRequest): com.mommydndn.app.data.network.feature.care.response.CreateAgencyCareProviderResponse
}
