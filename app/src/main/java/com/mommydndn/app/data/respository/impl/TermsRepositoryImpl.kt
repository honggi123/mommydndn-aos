package com.mommydndn.app.data.respository.impl

import com.mommydndn.app.data.api.TermsService
import com.mommydndn.app.data.model.TermsItem
import com.mommydndn.app.data.respository.TermsRepository
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class TermsRepositoryImpl @Inject constructor(
    private val termsService: TermsService,
) : TermsRepository {
    override suspend fun fetchAllTerms(): ApiResponse<List<TermsItem>> =
        termsService.fetchTermsItems()

}