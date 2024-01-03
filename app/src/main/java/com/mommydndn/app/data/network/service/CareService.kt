package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.api.model.response.GetJobSeekersResponse
import com.mommydndn.app.data.model.care.MinHourlySalary
import com.mommydndn.app.data.model.care.summary.CompanySummary
import com.mommydndn.app.data.model.care.summary.JobOfferSummary
import com.mommydndn.app.data.model.care.summary.JobSeekerSummary
import com.mommydndn.app.data.network.model.request.CompanyCreationRequest
import com.mommydndn.app.data.network.model.request.CompanyListRequest
import com.mommydndn.app.data.network.model.request.JobOfferCreationRequest
import com.mommydndn.app.data.network.model.request.JobOfferListRequest
import com.mommydndn.app.data.network.model.request.JobSeekerCreationRequest
import com.mommydndn.app.data.network.model.request.JobSeekerListRequest
import com.mommydndn.app.data.network.model.response.CaringTypeResponse
import com.mommydndn.app.data.network.model.response.CompanyCreationResponse
import com.mommydndn.app.data.network.model.response.CompanyEtcCheckItem
import com.mommydndn.app.data.network.model.response.GetNearestJobOffersResponse
import com.mommydndn.app.data.network.model.response.IndividualEtcCheckItem
import com.mommydndn.app.data.network.model.response.JobOfferCreationResponse
import com.mommydndn.app.data.network.model.response.JobOfferResponse
import com.mommydndn.app.data.network.model.response.JobSeekerCreationResponse
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
    suspend fun fetchIndividualEtcCheckList(): List<IndividualEtcCheckItem>

    @GET("/api/caring/com-other-condition")
    suspend fun fetchCompanyEtcCheckList(): List<CompanyEtcCheckItem>

    @GET("/api/caring/caring-type")
    suspend fun fetchCaringTypesResponse(): List<CaringTypeResponse>

    @GET("/api/caring/min-hourly-salary")
    suspend fun fetchMinHourlySalary(): MinHourlySalary

    @POST("/api/caring/job-offer")
    suspend fun createJobOffer(@Body request: JobOfferCreationRequest): JobOfferCreationResponse

    @POST("/api/caring/company")
    suspend fun createCompany(@Body request: CompanyCreationRequest): CompanyCreationResponse

    @POST("/api/caring/job-seeker")
    suspend fun createJobSeeker(@Body request: JobSeekerCreationRequest): JobSeekerCreationResponse

    @GET("/api/caring/job-offer/{jobOfferId}")
    suspend fun fetchJobOffer(@Path("jobOfferId") id: Int): JobOfferResponse

    @POST("/api/caring/job-offer/list")
    suspend fun fetchJobOfferSummary(@Body request: JobOfferListRequest): Response<JobOfferSummary>

    @POST("/api/caring/job-seeker/list")
    suspend fun fetchJobSeekerSummary(@Body request: JobSeekerListRequest): Response<JobSeekerSummary>

    @POST("/api/caring/company/list")
    suspend fun fetchCompanySummary(@Body request: CompanyListRequest): Response<CompanySummary>
}
//import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListRequest
//import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListResponse
//import retrofit2.http.Body
//import retrofit2.http.POST
//
//interface CareService {
//
//    @POST("/api/caring/job-offer/list")
//    suspend fun getCareJobOpeningList(
//        @Body request: GetCareJobOpeningListRequest
//    ): GetCareJobOpeningListResponse
//}