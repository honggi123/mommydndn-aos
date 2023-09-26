package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.TermsItemResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface TermsService {
    @GET("/api/terms")
    suspend fun fetchTermsItems(
    ): ApiResponse<List<TermsItemResponse>>
}
