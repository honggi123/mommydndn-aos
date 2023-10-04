package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.LoginRequest
import com.mommydndn.app.data.api.model.LoginResponse
import com.mommydndn.app.data.api.model.NearestJobOfferResponse
import com.mommydndn.app.data.api.model.NearestJobSeekerResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface CaringService {
    @GET("/api/caring/job-seeker/nearest")
    suspend fun fetchNearestJobSeeker(): ApiResponse<List<NearestJobSeekerResponse>>

    @GET("/api/caring/job-offer/nearest")
    suspend fun fetchNearestJobOffer(): ApiResponse<List<NearestJobOfferResponse>>
}