package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.di.IODispatcher
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNeighborhoodByQueryUseCase @Inject constructor(
    @IODispatcher coroutineDispatcher: CoroutineDispatcher,
    private val repository: LocationRepository
) : FlowUseCase<String, PagingData<Neighborhood>>(coroutineDispatcher) {

    override fun execute(parameters: String): Flow<PagingData<Neighborhood>> {
        return repository.searchNeighborhoodByQuery(parameters)
    }
}
