package com.mommydndn.app.domain.usecase.tos

import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTermsOfServiceUseCase @Inject constructor(
    private val repository: TermsOfServiceRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<TermsOfService>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): List<TermsOfService> {
        return repository.fetchTermsOfService()
    }
}
