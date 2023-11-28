package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditions
import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditionsRequest
import com.mommydndn.app.data.api.service.TermsAndConditionsService
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TermsAndConditionsDataRepository @Inject constructor(
    private val termsAndConditionsService: TermsAndConditionsService,
) : TermsAndConditionsRepository {

    override fun fetchAllTerms() = flow {
        val value = termsAndConditionsService.fetchTermsItems()
        emit(value)
    }.flowOn(Dispatchers.IO)

    override suspend fun updateTermsCheckedStatus(termsItems: List<TermsItem>) {
        val approvalRequestList = termsItems.map {
            UpdateTermsAndConditionsRequest(
                termsId = it.termsId,
                isApproved = it.isSelected
            )
        }
        termsAndConditionsService.updateTermsApproval(approvalRequestList)
    }

}