package com.mommydndn.app.data.repository

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.Coordinates
import com.mommydndn.app.domain.model.Neighborhood
import com.mommydndn.app.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationDataRepository @Inject constructor(
) : LocationRepository {

    override fun searchNeighborhoodByCoordinates(coordinates: Coordinates): Flow<PagingData<Neighborhood>> {
        TODO("Not yet implemented")
    }

    override fun searchNeighborhoodByQuery(query: String): Flow<PagingData<Neighborhood>> {
        TODO()
    }
}