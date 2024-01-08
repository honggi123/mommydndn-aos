package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.tos.GetTosListResponse
import retrofit2.http.GET

interface TosService {

    @GET("/api/terms")
    suspend fun getTosList(): GetTosListResponse

}