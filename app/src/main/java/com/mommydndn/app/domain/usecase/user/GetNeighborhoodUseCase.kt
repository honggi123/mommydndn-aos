package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNeighborhoodUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
) : FlowUseCase<Unit, Neighborhood>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<Neighborhood> {
        TODO()
    }
}