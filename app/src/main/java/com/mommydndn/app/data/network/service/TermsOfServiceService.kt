package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.UpdateTermsOfServiceListRequest
import com.mommydndn.app.data.api.model.response.GetTermsOfServiceListResponse
import com.mommydndn.app.data.api.model.response.GetTermsOfServiceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsOfServiceService {

    @GET("/api/terms")
    suspend fun fetchTermsOfService(): GetTermsOfServiceListResponse

    @POST("api/user/terms")
    suspend fun updateTosApproval(@Body request: UpdateTermsOfServiceListRequest): Unit
}
