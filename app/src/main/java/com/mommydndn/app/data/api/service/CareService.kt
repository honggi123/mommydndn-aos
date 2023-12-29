package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.CompanyCreationRequest
import com.mommydndn.app.data.api.model.request.CompanyListRequest
import com.mommydndn.app.data.api.model.request.JobOfferListRequest
import com.mommydndn.app.data.api.model.request.JobOfferCreationRequest
import com.mommydndn.app.data.api.model.request.JobSeekerCreationRequest
import com.mommydndn.app.data.api.model.request.JobSeekerListRequest
import com.mommydndn.app.data.api.model.response.CaringTypeResponse
import com.mommydndn.app.data.api.model.response.CompanyCreationResponse
import com.mommydndn.app.data.api.model.response.CompanyEtcCheckItem
import com.mommydndn.app.data.api.model.response.GetJobSeekersResponse
import com.mommydndn.app.data.api.model.response.IndividualEtcCheckItem
import com.mommydndn.app.data.api.model.response.JobOfferCreationResponse
import com.mommydndn.app.data.api.model.response.JobOfferResponse
import com.mommydndn.app.data.api.model.response.JobSeekerCreationResponse
import com.mommydndn.app.data.api.model.response.GetNearestJobOffersResponse
import com.mommydndn.app.data.model.care.summary.JobOfferSummary
import com.mommydndn.app.data.model.care.summary.JobSeekerSummary
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.summary.CompanySummary
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CareService {

    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestJobSeeker(): GetJobSeekersResponse

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOffer(): GetNearestJobOffersResponse

    @GET("/api/caring/ind-other-condition")
    suspend fun fetchIndividualEtcCheckList(): ApiResponse<List<IndividualEtcCheckItem>>

    @GET("/api/caring/com-other-condition")
    suspend fun fetchCompanyEtcCheckList(): ApiResponse<List<CompanyEtcCheckItem>>

    @GET("/api/caring/caring-type")
    suspend fun fetchCaringTypesResponse(): ApiResponse<List<CaringTypeResponse>>

    @GET("/api/caring/min-hourly-salary")
    suspend fun fetchMinHourlySalary(): ApiResponse<MinHourlySalary>

    @POST("/api/caring/job-offer")
    suspend fun createJobOffer(@Body request: JobOfferCreationRequest): ApiResponse<JobOfferCreationResponse>

    @POST("/api/caring/company")
    suspend fun createCompany(@Body request: CompanyCreationRequest): ApiResponse<CompanyCreationResponse>

    @POST("/api/caring/job-seeker")
    suspend fun createJobSeeker(@Body request: JobSeekerCreationRequest): ApiResponse<JobSeekerCreationResponse>

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun fetchJobOffer(@Path("jobOfferId") id: Int): ApiResponse<JobOfferResponse>

    @POST("/api/caring/job-offer/list")
    suspend fun fetchJobOfferSummary(@Body request: JobOfferListRequest): Response<JobOfferSummary>

    @POST("/api/caring/job-seeker/list")
    suspend fun fetchJobSeekerSummary(@Body request: JobSeekerListRequest): Response<JobSeekerSummary>

    @POST("/api/caring/company/list")
    suspend fun fetchCompanySummary(@Body request: CompanyListRequest): Response<CompanySummary>
}