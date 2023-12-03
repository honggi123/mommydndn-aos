package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.TermsAndConditions.TermsAndConditionsItem

interface TermsAndConditionsRepository {

    suspend fun fetchAllTermsAndConditions(): List<TermsAndConditionsItem>

    suspend fun updateTermsCheckedStatus(items: List<TermsAndConditionsItem>)
}
