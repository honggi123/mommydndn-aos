package com.mommydndn.app.domain.usecase.terms

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTermsOfServiceStateUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: TermsOfServiceRepository,
) : UseCase<UpdateTermsOfServiceStateParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: UpdateTermsOfServiceStateParams) {
        return repository.updateTermsOfServiceAgreement(parameters)
    }
}

typealias UpdateTermsOfServiceStateParams = Map<TermsOfService, Boolean>

