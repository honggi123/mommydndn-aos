package com.mommydndn.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.AddressResponse
import com.mommydndn.app.data.api.service.KakaoApiService
import com.mommydndn.app.data.api.service.LocationService
import com.mommydndn.app.data.datasource.pagingsource.LocationsByKeywordPagingSource
import com.mommydndn.app.data.datasource.pagingsource.NearestByLocationPagingSource
import com.mommydndn.app.data.model.map.EmdItem
import com.mommydndn.app.data.model.map.LocationInfo
import com.mommydndn.app.domain.repository.LocationRepository
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationDataRepository @Inject constructor(
    private val locationService: LocationService,
    private val kakaoApiService: KakaoApiService
) : LocationRepository {

    override fun fetchEmdByLocation(locationInfo: LocationInfo): Flow<EmdItem?> = flow {
        locationService.fetchNearestByLocation(
            latitude = locationInfo.latitude,
            longitude = locationInfo.longitude
        ).suspendOnSuccess {
            emit(data.emdList.get(0))
        }
    }.flowOn(Dispatchers.IO)


    override fun fetchNearestByLocation(
        locationInfo: LocationInfo
    ): Flow<PagingData<EmdItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { NearestByLocationPagingSource(locationInfo, locationService) }
        ).flow
    }

    override fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<EmdItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { LocationsByKeywordPagingSource(keyword, locationService) }
        ).flow
    }

    override fun fetchAddressByKeyword(keyword: String): Flow<AddressResponse> = flow {
        kakaoApiService.fetchAddressInfo(query = keyword).suspendOnSuccess {
            emit(data)
        }
    }.flowOn(Dispatchers.IO)


}