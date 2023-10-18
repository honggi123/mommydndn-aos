package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.terms.TermsItem
import kotlinx.coroutines.flow.Flow

interface TermsRepository {
    fun fetchAllTerms(): Flow<List<TermsItem>>

    suspend fun updateTermsCheckedStatus(items: List<TermsItem>)
}