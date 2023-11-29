package com.mommydndn.app.domain.usecase.terms

import com.mommydndn.app.data.api.model.response.GetTermsAndConditionsResponse
import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import com.mommydndn.app.domain.usecase.UseCase
import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllTermsUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: TermsAndConditionsRepository,
) : FlowUseCase<Unit, List<TermsItem>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<List<TermsItem>> {
        return repository.fetchAllTerms().map { mapToTermsItemList(it) }
    }

    private fun mapToTermsItemList(response: GetTermsAndConditionsResponse): List<TermsItem> {
        return response.map { getTermsAndConditions ->
            TermsItem(
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





