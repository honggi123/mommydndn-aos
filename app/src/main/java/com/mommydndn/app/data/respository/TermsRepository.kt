package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.TermsItem
import com.skydoves.sandwich.ApiResponse

interface TermsRepository {
    suspend fun fetchAllTerms() : ApiResponse<List<TermsItem>>
}