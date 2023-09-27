package com.mommydndn.app.data.respository

import com.mommydndn.app.data.model.TermsItem
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface TermsRepository {
    suspend fun fetchAllTerms(): Flow<List<TermsItem>>

    suspend fun updateTermsCheckedStatus(items: List<TermsItem>)
}