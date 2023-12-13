package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationsUseCase @Inject constructor(
    private val repositiry: LocationRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<String, PagingData<LocationInfo>>(coroutineDispatcher) {
    override fun execute(keyword: String): Flow<PagingData<LocationInfo>> {
        return repositiry.fetchLocationsByKeyword(keyword)
    }
}
