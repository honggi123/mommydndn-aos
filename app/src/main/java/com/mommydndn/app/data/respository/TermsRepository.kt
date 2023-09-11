package com.mommydndn.app.data.respository

import com.mommydndn.app.data.dto.TermsItem
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.model.LoginType
import com.skydoves.sandwich.ApiResponse

interface TermsRepository {
    suspend fun fetchAllTerms() : ApiResponse<List<TermsItem>>
}