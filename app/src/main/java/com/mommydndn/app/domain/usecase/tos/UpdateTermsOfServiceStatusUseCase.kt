package com.mommydndn.app.domain.usecase.tos

import com.mommydndn.app.domain.repository.TermsOfServiceRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTermsOfServiceStatusUseCase @Inject constructor(
    private val repository: TermsOfServiceRepository,
) : UseCase<UpdateTermsOfServiceParams, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: UpdateTermsOfServiceParams) {
        return with(parameters) {
            repository.updateTermsCheckedStatus(item)
        }
    }
}

data class UpdateTermsOfServiceParams(
    val item: List<TermsOfServiceApprovedStatus>,
)
