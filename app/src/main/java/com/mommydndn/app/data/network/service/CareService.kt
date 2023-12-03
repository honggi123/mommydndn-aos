package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.care.GetCareJobOfferListRequest
import com.mommydndn.app.data.network.model.care.GetCareJobOfferListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CareService {

    @POST("/api/caring/job-offer/list")
    suspend fun getCareJobOfferList(
        @Body request: GetCareJobOfferListRequest
    ): GetCareJobOfferListResponse
}