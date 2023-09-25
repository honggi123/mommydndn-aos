package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.EmdItem
import com.mommydndn.app.data.model.LocationInfo
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
     fun fetchNearestByLocation(locationInfo: LocationInfo): Flow<PagingData<EmdItem>>

    fun fetchNearestByKeyword(keyword: String): Flow<PagingData<EmdItem>>

}