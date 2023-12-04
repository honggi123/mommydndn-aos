package com.mommydndn.app.data.repository

import com.mommydndn.app.data.api.model.request.UpdateTermsAndConditions
import com.mommydndn.app.data.api.model.response.toDomain
import com.mommydndn.app.data.api.service.TermsOfServiceService
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import javax.inject.Inject

class TermsOfServiceDataRepository @Inject constructor(
    private val termsOfServiceService: TermsOfServiceService,
) : TermsOfServiceRepository {

    override suspend fun fetchTermsOfService(): List<TermsOfService> {
        return termsOfServiceService.fetchTermsOfService().map { it.toDomain() }
    }

    override suspend fun updateTermsCheckedStatus(termsItems: List<TermsOfServiceApprovedStatus>) {
        val approvalRequestList = termsItems.map {
            UpdateTermsAndConditions(
                termsId = it.id,
                isApproved = it.isApproved
            )
        }
        termsOfServiceService.updateTermsApproval(approvalRequestList)
    }
}
