package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.api.model.response.GetTermsAndConditionsResponse
import com.mommydndn.app.data.model.terms.TermsItem
import kotlinx.coroutines.flow.Flow

interface TermsAndConditionsRepository {

    fun fetchAllTerms(): Flow<GetTermsAndConditionsResponse>

    suspend fun updateTermsCheckedStatus(items: List<TermsItem>)
}