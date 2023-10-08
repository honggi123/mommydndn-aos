package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.NearestJobOfferResponse
import com.mommydndn.app.data.model.JobSeeker
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface CaringService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestJobSeeker(): ApiResponse<List<JobSeeker>>

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOffer(): ApiResponse<List<NearestJobOfferResponse>>
}