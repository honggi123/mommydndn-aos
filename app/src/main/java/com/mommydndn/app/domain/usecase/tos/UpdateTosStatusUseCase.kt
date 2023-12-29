package com.mommydndn.app.domain.usecase.tos

import com.mommydndn.app.domain.model.tos.TosAgreementStatus
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTosStatusUseCase @Inject constructor(
    private val repository: TermsOfServiceRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<UpdateTosParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: UpdateTosParams) {
        return with(parameters) {
            repository.updateTosApprovedStatus(item)
        }
    }
}

data class UpdateTosParams(
    val item: List<TosAgreementStatus>,
)
