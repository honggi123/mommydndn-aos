package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.TermsOfService

interface TermsOfServiceRepository {

    suspend fun getTermsOfService(): List<TermsOfService>

    suspend fun updateTermsOfServiceState(termsOfService: Map<TermsOfService, Boolean>)
}
