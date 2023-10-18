package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.TermsApprovalRequest
import com.mommydndn.app.data.api.model.response.TermsItemResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsService {
    @GET("/api/terms")
    suspend fun fetchTermsItems(
    ): ApiResponse<List<TermsItemResponse>>

    @POST("api/user/terms")
    suspend fun updateTermsApproval(
        @Body request: List<TermsApprovalRequest>
    ): ApiResponse<Unit>
}
