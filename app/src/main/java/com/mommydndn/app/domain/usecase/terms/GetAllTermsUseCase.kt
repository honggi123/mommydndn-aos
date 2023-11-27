package com.mommydndn.app.domain.usecase.terms

import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import com.mommydndn.app.domain.usecase.UseCase
import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllTermsUseCase @Inject constructor(
    coroutineDispatcher: CoroutineDispatcher,
    private val repository: TermsAndConditionsRepository,
) : FlowUseCase<GetAllTermsParams, List<TermsItem>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<Result<List<TermsItem>>> {
        return with(parameters) {
            repository.fetchAllTerms()
        }
    }

    override suspend fun execute(parameters: GetAllTermsParams): Flow<Result<List<TermsItem>>> {
        TODO("Not yet implemented")
    }
}





