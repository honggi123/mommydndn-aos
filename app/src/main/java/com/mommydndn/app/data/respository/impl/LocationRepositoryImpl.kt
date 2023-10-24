package com.mommydndn.app.data.respository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.AddressDocument
import com.mommydndn.app.data.api.service.KakaoApiService
import com.mommydndn.app.data.api.service.MapService
import com.mommydndn.app.data.datasource.pagingsource.AddressByKeyWordPagingSource
import com.mommydndn.app.data.datasource.pagingsource.LocationsByKeywordPagingSource
import com.mommydndn.app.data.datasource.pagingsource.NearestByLocationPagingSource
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.data.respository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val mapService: MapService,
    private val kakaoApiService: KakaoApiService
) : LocationRepository {
    override fun fetchNearestByLocation(
        locationInfo: LocationInfo
    ): Flow<PagingData<EmdItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { NearestByLocationPagingSource(locationInfo, mapService) }
        ).flow
    }

    override fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<EmdItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { LocationsByKeywordPagingSource(keyword, mapService) }
        ).flow
    }

    override fun fetchAddressByKeyword(keyword: String): Flow<PagingData<AddressDocument>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { AddressByKeyWordPagingSource(keyword, kakaoApiService) }
        ).flow
    }


}