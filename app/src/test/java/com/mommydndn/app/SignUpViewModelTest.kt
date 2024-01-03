package com.mommydndn.app

import androidx.paging.PagingData
import com.mommydndn.app.domain.model.location.Coordinates
import com.mommydndn.app.domain.model.location.Neighborhood
import com.mommydndn.app.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignUpViewModelTest {

}

class FakeLocationRepository : LocationRepository {

    override fun searchNeighborhoodByCoordinates(
        coordinates: Coordinates
    ): Flow<PagingData<Neighborhood>> = flow {
        buildList {
            repeat(10) {
                add(Neighborhood(id = it, name = "c_name_${it}", address = "c_address_${it}"))
            }
        }.let {
            emit(PagingData.from(it))
        }
    }

    override fun searchNeighborhoodByQuery(keyword: String): Flow<PagingData<Neighborhood>> = flow {
        buildList {
            repeat(10) {
                add(Neighborhood(id = it, name = "q_name_${it}", address = "q_address_${it}"))
            }
        }.let {
            emit(PagingData.from(it))
        }
    }
}