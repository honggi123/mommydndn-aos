package com.mommydndn.app.domain.usecase.termsAndConditions

import com.mommydndn.app.domain.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTermsAndConditionsStatusUseCase @Inject constructor(
    private val repository: TermsAndConditionsRepository,
) : UseCase<UpdateTermsAndConditionsParams, Unit>(Dispatchers.IO) {

    override suspend fun execute(parameters: UpdateTermsAndConditionsParams) {
        return with(parameters) {
            repository.updateTermsCheckedStatus(item)
        }
    }
}

data class UpdateTermsAndConditionsParams(
    val item: List<TermsAndConditionsItem>,
)
