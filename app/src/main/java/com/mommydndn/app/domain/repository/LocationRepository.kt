package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.AddressResponse
import com.mommydndn.app.data.model.location.EmdItem
import com.mommydndn.app.data.model.location.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun fetchEmdByLocation(locationInfo: LocationInfo): EmdItem?

    fun fetchNearestLocations(locationInfo: LocationInfo): Flow<PagingData<EmdItem>>

    fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<EmdItem>>

    fun fetchAddressByKeyword(keyword: String): Flow<AddressResponse>
}