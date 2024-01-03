package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.model.request.UpdateTermsOfService
import com.mommydndn.app.data.network.model.response.GetTermsOfServiceItem
import com.mommydndn.app.data.network.service.TermsOfServiceService
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TermsOfServiceDataRepository @Inject constructor(
    private val service: TermsOfServiceService,
) : TermsOfServiceRepository {

    override suspend fun getTermsOfService(): List<TermsOfService> {
        return service.getTermsOfService().map(::toEntity)
    }

    override suspend fun updateTermsOfServiceState(termsOfService: Map<TermsOfService, Boolean>) {
        termsOfService.map { entry ->
            UpdateTermsOfService(entry.key.id, entry.value)
        }.let {
            service.updateTermsOfServiceState(it)
        }
    }
}

// TODO
fun toEntity(item: GetTermsOfServiceItem) = with(item) {
    TermsOfService(
        id = termsId,
        name = name,
        url = url,
        isRequired = isRequired,
    )
}