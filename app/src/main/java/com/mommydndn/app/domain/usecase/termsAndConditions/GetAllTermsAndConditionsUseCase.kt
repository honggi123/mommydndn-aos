package com.mommydndn.app.domain.usecase.termsAndConditions

import com.mommydndn.app.data.api.model.response.GetTermsAndConditionsResponse
import com.mommydndn.app.data.model.TermsAndConditions.TermsAndConditionsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllTermsAndConditionsUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: TermsAndConditionsRepository,
) : FlowUseCase<Unit, List<TermsAndConditionsItem>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<List<TermsAndConditionsItem>> {
        return repository.fetchAllTermsAndConditions().map { mapToTermsItemList(it) }
    }

    private fun mapToTermsItemList(response: GetTermsAndConditionsResponse): List<TermsAndConditionsItem> {
        return response.map { getTermsAndConditions ->
            TermsAndConditionsItem(
                createdAt = getTermsAndConditions.createdAt,
                isRequired = getTermsAndConditions.isRequired,
                name = getTermsAndConditions.name,
                termsId = getTermsAndConditions.termsId,
                updateAt = getTermsAndConditions.updateAt,
                url = getTermsAndConditions.url
            )
        }
    }

}





