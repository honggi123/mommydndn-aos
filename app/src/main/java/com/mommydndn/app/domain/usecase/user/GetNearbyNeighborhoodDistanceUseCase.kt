package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.care.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyNeighborhoodDistanceUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) : FlowUseCase<Unit, NearbyNeighborhoodDistance>(coroutineDispatcher) {

    override fun execute(parameters: Unit): Flow<NearbyNeighborhoodDistance> {
        return repository.getNearbyNeighborhoodDistance()
    }
}