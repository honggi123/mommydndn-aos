package com.mommydndn.app.data.api

import com.mommydndn.app.data.model.TermsItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface TermsService {
    @GET("/api/terms")
    suspend fun fetchTermsItems(
    ): ApiResponse<List<TermsItem>>
}
