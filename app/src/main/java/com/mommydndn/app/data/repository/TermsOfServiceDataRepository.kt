package com.mommydndn.app.data.repository

import com.mommydndn.app.data.network.service.TermsService
import com.mommydndn.app.domain.model.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TermsOfServiceDataRepository @Inject constructor(
    private val service: TermsService,
) : TermsOfServiceRepository {

    override suspend fun getTermsOfService(): List<TermsOfService> {
//        return service.getTosList().map(::toEntity)
        TODO()
    }

    override suspend fun updateTermsOfServiceState(termsOfService: Map<TermsOfService, Boolean>) {
//        termsOfService.map { entry ->
//            UpdateTosAgreementRequest(entry.key.id, entry.value)
//        }.let {
//            service.updateTermsOfServiceState(it)
//        }
        TODO()
    }
}

// TODO
//fun toEntity(item: GetTosItemResponse) = with(item) {
//    TermsOfService(
//        id = termsId,
//        name = name,
//        url = url,
//        isRequired = isRequired,
//    )
//}