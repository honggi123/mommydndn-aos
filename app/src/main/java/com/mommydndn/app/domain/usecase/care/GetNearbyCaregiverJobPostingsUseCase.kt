package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.CaregiverJobPosting
import com.mommydndn.app.domain.repository.CaregiverJobPostingRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyCaregiverJobPostingsUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: CaregiverJobPostingRepository,
) : UseCase<Unit, List<CaregiverJobPosting>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): List<CaregiverJobPosting> {
        return repository.getNearbyCaregiverJobPostings()
    }
}