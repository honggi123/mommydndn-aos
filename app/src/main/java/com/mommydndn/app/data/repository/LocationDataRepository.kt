package com.mommydndn.app.data.repository

import androidx.paging.PagingData
import com.mommydndn.app.data.network.model.response.AddressResponse
import com.mommydndn.app.domain.model.location.CoordinatesInfo
import com.mommydndn.app.domain.model.location.LocationInfo
import com.mommydndn.app.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationDataRepository @Inject constructor(
//    private val locationService: LocationService,
//    private val kakaoApiService: KakaoApiService
) : LocationRepository {

    override suspend fun fetchNearestLocation(coordinatesInfo: CoordinatesInfo): LocationInfo? {
//        locationService.fetchNearestByMyLocation(
//            latitude = coordinatesInfo.latitude,
//            longitude = coordinatesInfo.longitude
//        ).body()

        return TODO()
    }


    override fun fetchNearestLocations(coordinatesInfo: CoordinatesInfo): Flow<PagingData<LocationInfo>> {
        TODO()
//        return Pager(
//            config = PagingConfig(
//                pageSize = 15, enablePlaceholders = false
//            ),
//            pagingSourceFactory = { NearestByLocationPagingSource(coordinatesInfo, locationService) }
//        ).flow
    }

    override fun fetchLocationsByKeyword(keyword: String): Flow<PagingData<LocationInfo>> {
        TODO()
//        return Pager(
//            config = PagingConfig(
//                pageSize = 15, enablePlaceholders = false
//            ),
//            pagingSourceFactory = { LocationsByKeywordPagingSource(keyword, locationService) }
//        ).flow
    }

    override fun fetchAddressByKeyword(keyword: String): Flow<AddressResponse> = flow<AddressResponse> {
        TODO()
//        kakaoApiService.fetchAddressInfo(query = keyword).suspendOnSuccess {
//            emit(data)
//        }
    }.flowOn(Dispatchers.IO)


}