package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.api.model.response.GetTermsOfServiceListResponse
import com.mommydndn.app.data.network.model.request.UpdateTermsOfServiceListRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsOfServiceService {

    @GET("/api/terms")
    suspend fun fetchTermsOfService(): GetTermsOfServiceListResponse

    @POST("api/user/terms")
    suspend fun updateTosApproval(@Body request: UpdateTermsOfServiceListRequest): Unit
}
