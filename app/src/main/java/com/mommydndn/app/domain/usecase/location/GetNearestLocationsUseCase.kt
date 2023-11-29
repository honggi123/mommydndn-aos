package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<LocationInfo, PagingData<EmdItem>>(coroutineDispatcher) {
    override suspend fun execute(parameters: LocationInfo): Flow<PagingData<EmdItem>> {
        return locationRepository.fetchNearestByLocation(parameters)
    }

}