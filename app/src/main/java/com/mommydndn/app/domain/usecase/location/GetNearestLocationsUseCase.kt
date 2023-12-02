package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.data.model.location.EmdItem
import com.mommydndn.app.data.model.location.LocationInfo
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
) : FlowUseCase<LocationInfo, PagingData<EmdItem>>(Dispatchers.IO) {
    override suspend fun execute(parameters: LocationInfo): Flow<PagingData<EmdItem>> {
        return locationRepository.fetchNearestLocations(parameters)
    }

}