package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.tos.TosAgreementStatus
import com.mommydndn.app.domain.model.tos.TermsOfService

interface TermsOfServiceRepository {

    suspend fun fetchTermsOfService(): List<TermsOfService>

    suspend fun updateTosApprovedStatus(items: List<TosAgreementStatus>)
}
