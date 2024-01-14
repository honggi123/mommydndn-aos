package com.mommydndn.app.data.network.service.care

import com.mommydndn.app.data.network.service.care.request.CreateAgencyCareWorkerRequest
import com.mommydndn.app.data.network.service.care.request.GetAgencyCareWorkerListRequest
import com.mommydndn.app.data.network.service.care.request.CreateCareJobRequest
import com.mommydndn.app.data.network.service.care.request.GetCareJobListRequest
import com.mommydndn.app.data.network.service.care.request.CreateCareWorkerRequest
import com.mommydndn.app.data.network.service.care.request.GetCareWorkerListRequest
import com.mommydndn.app.data.network.service.care.response.CreateAgencyCareWorkerResponse
import com.mommydndn.app.data.network.service.care.response.CreateCareJobResponse
import com.mommydndn.app.data.network.service.care.response.CreateCareWorkerResponse
import com.mommydndn.app.data.network.service.care.response.GetAgencyCareWorkerResponse
import com.mommydndn.app.data.network.service.care.response.GetAgencyCareWorkerSummaryListResponse
import com.mommydndn.app.data.network.service.care.response.GetCareWorkerResponse
import com.mommydndn.app.data.network.service.care.response.GetCareWorkerSummaryListResponse
import com.mommydndn.app.data.network.service.care.response.GetCareTypeListResponse
import com.mommydndn.app.data.network.service.care.response.GetCareJobResponse
import com.mommydndn.app.data.network.service.care.response.GetCareJobSummaryListResponse
import com.mommydndn.app.data.network.service.care.response.GetMinimumWageResponse
import com.mommydndn.app.data.network.service.care.response.GetOtherAgenyConditionListResponse
import com.mommydndn.app.data.network.service.care.response.GetOtherIndividualConditionListResponse
import com.mommydndn.app.data.network.service.location.response.GetNearestCareJobListResponse
import com.mommydndn.app.data.network.service.location.response.GetNearestCareWorkerListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun getNearestCareWorkerList(): GetNearestCareWorkerListResponse

    @GET("/api/caring/job-offer/nearest")
    suspend fun getNearestJobOpeningList(): GetNearestCareJobListResponse

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun getJobOpening(@Path("jobOfferId") id: Int): GetCareJobResponse

    @GET("/api/caring/job-seeker/{jobSeekerId}")
    suspend fun getCareWorker(@Path("jobSeekerId") id: Int): GetCareWorkerResponse

    @GET("/api/caring/company/{companyId}")
    suspend fun getAgencyCareWorker(@Path("companyId") id: Int): GetAgencyCareWorkerResponse

    @POST("/api/caring/job-offer/list")
    suspend fun getJobOpeningSummaryList(@Body request: GetCareJobListRequest): GetCareJobSummaryListResponse

    @POST("/api/caring/job-seeker/list")
    suspend fun getCareWorkerSummaryList(@Body request: GetCareWorkerListRequest): GetCareWorkerSummaryListResponse

    @POST("/api/caring/company/list")
    suspend fun getAgencyCareWorkerSummaryList(@Body request: GetAgencyCareWorkerListRequest): GetAgencyCareWorkerSummaryListResponse

    @GET("/api/caring/min-hourly-salary")
    suspend fun getMininumWage(): GetMinimumWageResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun getOtherIndividualConditions(): GetOtherIndividualConditionListResponse

    @GET("/api/caring/com-other-condition")
    suspend fun getOtherAgencyCondtions(): GetOtherAgenyConditionListResponse

    @GET("/api/caring/caring-type")
    suspend fun getCareTypes(): GetCareTypeListResponse

    @POST("/api/caring/job-offer")
    suspend fun createJobOpening(@Body request: CreateCareJobRequest): CreateCareJobResponse

    @POST("/api/caring/job-seeker")
    suspend fun createCareWorker(@Body request: CreateCareWorkerRequest): CreateCareWorkerResponse

    @POST("/api/caring/company")
    suspend fun createAgencyCareWorker(@Body request: CreateAgencyCareWorkerRequest): CreateAgencyCareWorkerResponse
}
