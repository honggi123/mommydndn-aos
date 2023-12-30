package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.network.model.response.AddressResponse
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun fetchNearestLocation(coordinatesInfo: CoordinatesInfo): LocationInfo?

    fun fetchNearestLocations(coordinatesInfo: CoordinatesInfo): Flow<PagingData<LocationInfo>>

    fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<LocationInfo>>

    fun fetchAddressByKeyword(keyword: String): Flow<AddressResponse>
}