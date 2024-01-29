package com.mommydndn.app.domain.usecase.tos

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTermsOfServiceConsentUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: TermsOfServiceRepository,
) : UseCase<UpdateTermsOfServiceConsentParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: UpdateTermsOfServiceConsentParams) {
        return repository.updateTermsOfServiceAgreement(parameters)
    }
}

typealias UpdateTermsOfServiceConsentParams = Map<TermsOfService, Boolean>

