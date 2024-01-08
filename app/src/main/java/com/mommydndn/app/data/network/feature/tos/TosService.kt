package com.mommydndn.app.data.network.feature.tos

import retrofit2.http.GET

interface TosService {

    @GET("/api/terms")
    suspend fun getTosList(): GetTosListResponse

}