package com.mommydndn.app.domain.usecase.termsAndConditions

import com.mommydndn.app.data.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTermsUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: TermsAndConditionsRepository,
) : UseCase<UpdateTermsParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameters: UpdateTermsParams) {
        return with(parameters) {
            repository.updateTermsCheckedStatus(item)
        }
    }
}

data class UpdateTermsParams(
    val item: List<TermsAndConditionsItem>,
)
