package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.NetworkTermsOfService
import retrofit2.http.GET

interface TermsService {

    @GET("/api/terms")
    suspend fun getTermsOfService(): List<NetworkTermsOfService>
}