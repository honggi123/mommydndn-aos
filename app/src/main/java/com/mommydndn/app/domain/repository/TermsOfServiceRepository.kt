package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.TermsOfService

interface TermsOfServiceRepository {

    suspend fun getTermsOfService(): List<TermsOfService>

    suspend fun updateTermsOfServiceAgreement(termsOfService: Map<TermsOfService, Boolean>)
}
