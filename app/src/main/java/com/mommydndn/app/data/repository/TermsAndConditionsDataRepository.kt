package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditions
import com.mommydndn.app.data.api.model.response.toDomain
import com.mommydndn.app.data.api.service.TermsAndConditionsService
import com.mommydndn.app.domain.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import javax.inject.Inject

class TermsAndConditionsDataRepository @Inject constructor(
    private val termsAndConditionsService: TermsAndConditionsService,
) : TermsAndConditionsRepository {

    override suspend fun fetchAllTermsAndConditions(): List<TermsAndConditionsItem> {
       return termsAndConditionsService.fetchTermsItems().map { it.toDomain() }
    }

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