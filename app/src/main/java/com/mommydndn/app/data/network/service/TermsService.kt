package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkTermsOfService
import com.mommydndn.app.data.network.service.request.UpdateTermsOfServiceAgreementRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsService {

    @GET("/api/terms")
    suspend fun getTermsOfService(): List<NetworkTermsOfService>

    @POST("api/user/terms")
    suspend fun updateTermsOfServiceAgreement(@Body request: UpdateTermsOfServiceAgreementRequest)
}