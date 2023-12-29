package com.mommydndn.app.domain.usecase.location

import com.mommydndn.app.domain.repository.LocationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationsUseCase @Inject constructor(
    private val repositiry: LocationRepository
) {
    fun invoke(keyword: String) = repositiry.fetchLocationsByKeyword(keyword)
}
