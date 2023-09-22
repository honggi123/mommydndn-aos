package com.mommydndn.app.data.respository

import androidx.paging.PagingData
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.NearestResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun fetchNearestByLocation(
        latitude: Double,
        longitude: Double
    ): ApiResponse<NearestResponse>

    fun fetchNearestByKeyword(keyword: String): Flow<PagingData<EmdItem>>

}