package com.mommydndn.app.data.api.service

import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditions
import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditionsRequest
import com.mommydndn.app.data.api.model.response.GetTermsAndConditions
import com.mommydndn.app.data.api.model.response.GetTermsAndConditionsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TermsAndConditionsService {

    @GET("/api/terms")
    suspend fun fetchTermsItems(): ApiResponse<List<GetTermsAndConditions>>

    @GET("/api/terms")
    suspend fun getTermsAndConditions(): GetTermsAndConditionsResponse

    @POST("api/user/terms")
    suspend fun updateTermsApproval(@Body request: List<UpdateTermsAndConditionsRequest>): ApiResponse<Unit>
}
