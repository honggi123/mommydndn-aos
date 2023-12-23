package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListRequest
import com.mommydndn.app.data.network.model.care.GetCareJobOpeningListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CareService {

    @POST("/api/caring/job-offer/list")
    suspend fun getCareJobOpeningList(
        @Body request: GetCareJobOpeningListRequest
    ): GetCareJobOpeningListResponse
}