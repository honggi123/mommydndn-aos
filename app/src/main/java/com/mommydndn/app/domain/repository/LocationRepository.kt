package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.Coordinates
import com.mommydndn.app.domain.model.location.Neighborhood
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun searchNeighborhoodByCoordinates(coordinates: Coordinates): Flow<PagingData<Neighborhood>>

    fun searchNeighborhoodByQuery(keyword: String): Flow<PagingData<Neighborhood>>
}