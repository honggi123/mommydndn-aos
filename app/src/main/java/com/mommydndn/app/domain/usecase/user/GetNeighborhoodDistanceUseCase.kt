package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.user.NeighborhoodDistance
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNeighborhoodDistanceUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : FlowUseCase<Unit, NeighborhoodDistance>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<NeighborhoodDistance> {
        return repository.getNearbyNeighborhoodDistance()
    }
}