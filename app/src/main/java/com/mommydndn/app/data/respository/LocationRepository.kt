package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun fetchNearestByLocation(locationInfo: LocationInfo): Flow<PagingData<EmdItem>>

    fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<EmdItem>>

}