package com.mommydndn.app.domain.usecase.location

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.EmdItem
import com.mommydndn.app.domain.repository.LocationRepository
import com.mommydndn.app.domain.usecase.FlowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationsUseCase @Inject constructor(
    private val repositiry: LocationRepository,
) : FlowUseCase<String, PagingData<EmdItem>>(Dispatchers.IO) {
    override fun execute(keyword: String): Flow<PagingData<EmdItem>> {
        return repositiry.fetchLocationsByKeyword(keyword)
    }
}
