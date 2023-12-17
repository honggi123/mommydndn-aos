package com.mommydndn.app.domain.usecase.location

import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.repository.LocationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNearestLocationsUseCase @Inject constructor(
    private val repositiry: LocationRepository
) {
    fun invoke(parameters: CoordinatesInfo) = repositiry.fetchNearestLocations(parameters)
}
