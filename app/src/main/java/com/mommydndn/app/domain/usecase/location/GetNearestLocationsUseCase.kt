package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestLocationsUseCase @Inject constructor(
    private val repositiry: LocationRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<CoordinatesInfo, PagingData<LocationInfo>>(coroutineDispatcher) {

    override fun execute(parameters: CoordinatesInfo): Flow<PagingData<LocationInfo>> {
        return repositiry.fetchNearestLocations(parameters)
    }
}
