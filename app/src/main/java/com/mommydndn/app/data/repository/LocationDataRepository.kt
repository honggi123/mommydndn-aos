package com.mommydndn.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mommydndn.app.data.api.model.response.AddressResponse
import com.mommydndn.app.data.api.service.KakaoApiService
import com.mommydndn.app.data.api.service.LocationService
import com.mommydndn.app.data.datasource.pagingsource.LocationsByKeywordPagingSource
import com.mommydndn.app.data.datasource.pagingsource.NearestByLocationPagingSource
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.LocationInfo
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

    override fun fetchNearestLocation(coordinatesInfo: CoordinatesInfo): LocationInfo? {
       return locationService.fetchNearestByMyLocation(
            latitude = coordinatesInfo.latitude,
            longitude = coordinatesInfo.longitude
        ).body()
    }


    override fun fetchNearestLocations(coordinatesInfo: CoordinatesInfo): Flow<PagingData<LocationInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15, enablePlaceholders = false
            ),
            pagingSourceFactory = { NearestByLocationPagingSource(coordinatesInfo, locationService) }
        ).flow
    }

    override fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<LocationInfo>> {
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