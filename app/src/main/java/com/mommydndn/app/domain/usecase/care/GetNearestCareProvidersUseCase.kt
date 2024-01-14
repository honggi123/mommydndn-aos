package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.domain.model.care.JobSeeker
import com.mommydndn.app.domain.repository.CareRepository
import com.mommydndn.app.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestCareProvidersUseCase @Inject constructor(
    private val repository: CareRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UseCase<Unit, List<JobSeeker>>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): List<JobSeeker> {
        return repository.fetchNearestJobSeekers()
    }

}