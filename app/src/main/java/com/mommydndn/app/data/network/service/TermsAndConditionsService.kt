package com.mommydndn.app.data.network.service

import com.mommydndn.app.data.network.model.request.UpdateTermsAndConditionsRequest
import com.mommydndn.app.data.network.model.response.GetTermsAndConditions
import com.mommydndn.app.data.network.model.response.GetTermsAndConditionsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsAndConditionsService {

    @GET("/api/terms")
    suspend fun fetchTermsItems(
    ): ApiResponse<List<GetTermsAndConditions>>

    @GET("/api/terms")
    suspend fun getTermsAndConditions(): GetTermsAndConditionsResponse

    @POST("api/user/terms")
    suspend fun updateTermsApproval(
        @Body request: UpdateTermsAndConditionsRequest
    ): ApiResponse<Unit>
}
