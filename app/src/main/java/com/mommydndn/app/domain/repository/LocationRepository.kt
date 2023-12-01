package com.mommydndn.app.domain.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.network.model.response.AddressResponse
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun fetchEmdByLocation(locationInfo: LocationInfo): Flow<EmdItem?>

    fun fetchNearestByLocation(locationInfo: LocationInfo): Flow<PagingData<EmdItem>>

    fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<EmdItem>>

    fun fetchAddressByKeyword(keyword: String): Flow<AddressResponse>
}