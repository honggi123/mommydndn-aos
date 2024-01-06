package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.tos.GetTosListResponse
import com.mommydndn.app.data.network.model.tos.UpdateTosAgreementRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TosService {

    @GET("/api/terms")
    suspend fun getTermsOfService(): GetTosListResponse

}