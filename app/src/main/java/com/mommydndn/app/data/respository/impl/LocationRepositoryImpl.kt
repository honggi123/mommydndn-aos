package com.mommydndn.app.data.respository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.ApiService
import com.mommydndn.app.data.datasource.NearestByKeywordPagingSource
import com.mommydndn.app.data.datasource.NearestByLocationPagingSource
import com.mommydndn.app.data.model.EmdItem
import com.mommydndn.app.data.model.LocationInfo
import com.mommydndn.app.data.model.NearestResponse
import com.mommydndn.app.data.respository.LocationRepository
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LocationRepository {
    override fun fetchNearestByLocation(
        locationInfo: LocationInfo
    ): Flow<PagingData<EmdItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { NearestByLocationPagingSource(locationInfo, apiService) }
        ).flow
    }

    override fun fetchNearestByKeyword(keyword: String): Flow<PagingData<EmdItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { NearestByKeywordPagingSource(keyword, apiService) }
        ).flow
    }


}