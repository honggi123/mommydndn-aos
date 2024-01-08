package com.mommydndn.app.data.network.service.tos

import com.mommydndn.app.data.network.service.tos.response.GetTosListResponse
import retrofit2.http.GET

interface TosService {
    @GET("/api/terms")
    suspend fun getTosList(): GetTosListResponse
}