package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.request.UpdateTermsOfServiceRequest
import com.mommydndn.app.data.network.model.response.GetTermsOfServiceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsOfServiceService {

    @GET("/api/terms")
    suspend fun getTermsOfService(): GetTermsOfServiceResponse

    @POST("api/user/terms")
    suspend fun updateTermsOfServiceState(@Body request: UpdateTermsOfServiceRequest)
}