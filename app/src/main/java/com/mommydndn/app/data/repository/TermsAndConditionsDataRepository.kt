package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditions
import com.mommydndn.app.data.api.model.response.GetTermsAndConditionsResponse
import com.mommydndn.app.data.api.service.TermsAndConditionsService
import com.mommydndn.app.data.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TermsAndConditionsDataRepository @Inject constructor(
    private val termsAndConditionsService: TermsAndConditionsService,
) : TermsAndConditionsRepository {

    override fun fetchAllTermsAndConditions(): Flow<GetTermsAndConditionsResponse>  = flow {
        val value = termsAndConditionsService.fetchTermsItems()
        emit(value)
    }.flowOn(Dispatchers.IO)

    override suspend fun updateTermsCheckedStatus(termsItems: List<TermsAndConditionsItem>) {
        val approvalRequestList = termsItems.map {
            UpdateTermsAndConditions(
                termsId = it.termsId,
                isApproved = it.isSelected
            )
        }
        termsAndConditionsService.updateTermsApproval(approvalRequestList)
    }

}