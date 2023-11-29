package com.mommydndn.app.domain.repository

import com.mommydndn.app.data.api.model.response.GetTermsAndConditionsResponse
import com.mommydndn.app.data.model.TermsAndConditions.TermsAndConditionsItem
import kotlinx.coroutines.flow.Flow

interface TermsAndConditionsRepository {

    fun fetchAllTermsAndConditions(): Flow<GetTermsAndConditionsResponse>

    suspend fun updateTermsCheckedStatus(items: List<TermsAndConditionsItem>)
}