package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.model.user.request.UpdateTosAgreementRequest
import com.mommydndn.app.data.network.model.tos.GetTosItemResponse
import com.mommydndn.app.data.network.service.TosService
import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TermsOfServiceDataRepository @Inject constructor(
    private val service: TosService,
) : TermsOfServiceRepository {

    override suspend fun getTermsOfService(): List<TermsOfService> {
        return service.getTermsOfService().map(::toEntity)
    }

    override suspend fun updateTermsOfServiceState(termsOfService: Map<TermsOfService, Boolean>) {
        termsOfService.map { entry ->
            UpdateTosAgreementRequest(entry.key.id, entry.value)
        }.let {
            service.updateTermsOfServiceState(it)
        }
    }
}

// TODO
fun toEntity(item: GetTosItemResponse) = with(item) {
    TermsOfService(
        id = termsId,
        name = name,
        url = url,
        isRequired = isRequired,
    )
}