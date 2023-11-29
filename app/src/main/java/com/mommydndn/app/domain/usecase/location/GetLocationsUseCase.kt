package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.data.model.location.EmdItem
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationsUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<String, PagingData<EmdItem>>(coroutineDispatcher) {
    override suspend fun execute(keyword: String): Flow<PagingData<EmdItem>> {
        return locationRepository.fetchLocationsByKeyword(keyword)
    }

}