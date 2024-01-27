package com.mommydndn.app.domain.usecase.neighborhood

import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.Coordinates
import com.mommydndn.app.domain.model.NearbyNeighborhoodDistance
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.repository.UserRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearbyNeighborhoodsUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: UserRepository
) : FlowUseCase<Coordinates, Map<NearbyNeighborhoodDistance, List<Neighborhood>>>(coroutineDispatcher) {

    override fun execute(parameters: Coordinates): Flow<Map<NearbyNeighborhoodDistance, List<Neighborhood>>> {
        TODO()
    }
}