package com.mommydndn.app.domain.usecase.tos

import com.mommydndn.app.domain.model.tos.TermsOfService
import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTermsOfServiceStatusUseCase @Inject constructor(
    private val repository: TermsOfServiceRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<UpdateTermsOfServiceParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: UpdateTermsOfServiceParams) {
        return with(parameters) {
            repository.updateTermsCheckedStatus(item)
        }
    }
}

data class UpdateTermsOfServiceParams(
    val item: List<TermsOfService>,
)
