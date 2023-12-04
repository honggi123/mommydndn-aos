package com.mommydndn.app.domain.repository

import com.mommydndn.app.domain.model.tos.TermsOfService

interface TermsOfServiceRepository {

    suspend fun fetchTermsOfService(): List<TermsOfService>

    suspend fun updateTermsCheckedStatus(items: List<TermsOfServiceApprovedStatus>)
}
