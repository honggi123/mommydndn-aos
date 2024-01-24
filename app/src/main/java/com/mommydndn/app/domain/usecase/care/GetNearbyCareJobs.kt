package com.mommydndn.app.domain.usecase.care

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.CareJob
import com.mommydndn.app.domain.repository.CareJobRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyCareJobs @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: CareJobRepository,
) : FlowUseCase<Unit, List<CareJob>>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<List<CareJob>> {
        TODO()
    }
}