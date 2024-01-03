package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.location.Coordinates
import com.mommydndn.app.domain.model.location.Neighborhood
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNeighborhoodByCoordinatesUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: LocationRepository,
) : FlowUseCase<Coordinates, PagingData<Neighborhood>>(coroutineDispatcher) {

    override fun execute(parameters: Coordinates): Flow<PagingData<Neighborhood>> {
        return repository.searchNeighborhoodByCoordinates(parameters)
    }
}
