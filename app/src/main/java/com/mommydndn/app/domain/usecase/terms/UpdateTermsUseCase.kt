package com.mommydndn.app.domain.usecase.terms

import com.mommydndn.app.data.model.terms.TermsItem
import com.mommydndn.app.domain.repository.TermsAndConditionsRepository
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import com.mommydndn.app.domain.usecase.UseCase
import com.mommydndn.app.util.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
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
    val item: List<TermsItem>,
)
