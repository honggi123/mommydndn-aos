package com.mommydndn.app.data.repository

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.Coordinates
import com.mommydndn.app.domain.model.location.Neighborhood
import com.mommydndn.app.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationDataRepository @Inject constructor(
//    private val locationService: LocationService,
//    private val kakaoApiService: KakaoApiService
) : LocationRepository {

    override fun searchNeighborhoodByCoordinates(coordinates: Coordinates): Flow<PagingData<Neighborhood>> {
        TODO("Not yet implemented")
        //        locationService.fetchNearestByMyLocation(
//            latitude = coordinatesInfo.latitude,
//            longitude = coordinatesInfo.longitude
//        ).body()
        //        return Pager(
//            config = PagingConfig(
//                pageSize = 15, enablePlaceholders = false
//            ),
//            pagingSourceFactory = { NearestByLocationPagingSource(coordinatesInfo, locationService) }
//        ).flow
    }

    override fun searchNeighborhoodByQuery(keyword: String): Flow<PagingData<Neighborhood>> {
        TODO()
//        return Pager(
//            config = PagingConfig(
//                pageSize = 15, enablePlaceholders = false
//            ),
//            pagingSourceFactory = { LocationsByKeywordPagingSource(keyword, locationService) }
//        ).flow
    }


}