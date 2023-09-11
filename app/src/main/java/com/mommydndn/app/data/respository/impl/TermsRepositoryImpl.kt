package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.dto.TermsItem
import com.mommydndn.app.data.model.LoginResponse
import com.mommydndn.app.data.respository.TermsRepository
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class TermsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : TermsRepository {
    override suspend fun fetchAllTerms(): ApiResponse<List<TermsItem>> =
        apiService.fetchTermsItems()

}