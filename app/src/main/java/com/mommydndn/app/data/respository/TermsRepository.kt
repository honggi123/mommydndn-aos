package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.TermsItem
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface TermsRepository {
    fun fetchAllTerms(
        onComplete: () -> Unit,
        onError: (message: String?) -> Unit,
    ): Flow<List<TermsItem>>

    suspend fun updateTermsCheckedStatus(items: List<TermsItem>)
}