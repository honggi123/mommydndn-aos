package com.mommydndn.app.domain.usecase.termsAndConditions

import com.mommydndn.app.domain.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllTermsAndConditionsUseCase @Inject constructor(
    private val repository: TermsAndConditionsRepository,
) : UseCase<Unit, List<TermsAndConditionsItem>>(Dispatchers.IO) {

    override suspend fun execute(parameters: Unit): List<TermsAndConditionsItem> {
        return repository.fetchAllTermsAndConditions()
    }

}





