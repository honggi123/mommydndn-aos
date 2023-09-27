package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.TermsApprovalRequest
import com.mommydndn.app.data.api.model.TermsItemResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsService {
    @GET("/api/terms")
    suspend fun fetchTermsItems(
    ): ApiResponse<List<TermsItemResponse>>

    @POST("api/user/terms")
    fun updateTermsApproval(
        @Body request: List<TermsApprovalRequest>
    ): ApiResponse<Unit>
}
