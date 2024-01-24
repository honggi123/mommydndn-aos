package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkNearbyCareJob
import com.mommydndn.app.data.network.model.NetworkNearbyCareWorker
import com.mommydndn.app.data.network.service.request.GetCareAgenciesRequest
import com.mommydndn.app.data.network.service.request.GetCareJobsRequest
import com.mommydndn.app.data.network.service.request.GetCareWorkersRequest
import com.mommydndn.app.data.network.service.request.PostCareAgencyProfileRequest
import com.mommydndn.app.data.network.service.request.PostCareJobOpeningRequest
import com.mommydndn.app.data.network.service.request.PostCareWorkerProfileRequest
import com.mommydndn.app.data.network.service.response.GetCareAgenciesResponse
import com.mommydndn.app.data.network.service.response.GetCareAgencyDetailsResponse
import com.mommydndn.app.data.network.service.response.GetCareAgencyOtherConditionsResponse
import com.mommydndn.app.data.network.service.response.GetCareJobDetailsResponse
import com.mommydndn.app.data.network.service.response.GetCareJobsResponse
import com.mommydndn.app.data.network.service.response.GetCareTypesResponse
import com.mommydndn.app.data.network.service.response.GetCareWorkerDetailsResponse
import com.mommydndn.app.data.network.service.response.GetCareWorkerOtherConditionsResponse
import com.mommydndn.app.data.network.service.response.GetCareWorkersResponse
import com.mommydndn.app.data.network.service.response.GetMinimumHourlyPayResponse
import com.mommydndn.app.data.network.service.response.PostCareAgencyProfileResponse
import com.mommydndn.app.data.network.service.response.PostCareJobOpeningResponse
import com.mommydndn.app.data.network.service.response.PostCareWorkerProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareService {

    @GET("/api/caring/job-seeker/nearest")
    suspend fun getNearbyCareWorkers(): List<NetworkNearbyCareWorker>

    @GET("/api/caring/job-offer/nearest")
    suspend fun getNearbyCareJobs(): List<NetworkNearbyCareJob>

    @POST("/api/caring/job-offer/list")
    suspend fun getCareJobs(
        @Body request: GetCareJobsRequest
    ): GetCareJobsResponse

    @POST("/api/caring/job-seeker/list")
    suspend fun getCareWorkers(
        @Body request: GetCareWorkersRequest
    ): GetCareWorkersResponse

    @POST("/api/caring/company/list")
    suspend fun getCareAgencies(
        @Body request: GetCareAgenciesRequest
    ): GetCareAgenciesResponse

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun getCareJobDetails(
        @Path("jobOfferId") id: Int
    ): GetCareJobDetailsResponse

    @GET("/api/caring/job-seeker/{jobSeekerId}")
    suspend fun getCareWorkerDetails(
        @Path("jobSeekerId") id: Int
    ): GetCareWorkerDetailsResponse

    @GET("/api/caring/company/{companyId}")
    suspend fun getCareAgencyDetails(
        @Path("companyId") id: Int
    ): GetCareAgencyDetailsResponse

    @POST("/api/caring/job-offer")
    suspend fun postCareJobOpening(
        @Body request: PostCareJobOpeningRequest
    ): PostCareJobOpeningResponse

    @POST("/api/caring/job-seeker")
    suspend fun postCareWorkerProfile(
        @Body request: PostCareWorkerProfileRequest
    ): PostCareWorkerProfileResponse

    @POST("/api/caring/company")
    suspend fun postCareAgencyProfile(
        @Body request: PostCareAgencyProfileRequest
    ): PostCareAgencyProfileResponse

    @GET("/api/caring/caring-type")
    suspend fun getCareTypes(): GetCareTypesResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun getWorkerOtherConditions(): GetCareWorkerOtherConditionsResponse

    @GET("/api/caring/com-other-condition")
    suspend fun getAgencyOtherConditions(): GetCareAgencyOtherConditionsResponse

    @GET("/api/caring/min-hourly-salary")
    suspend fun getMinimumHourlyPay(): GetMinimumHourlyPayResponse
}
