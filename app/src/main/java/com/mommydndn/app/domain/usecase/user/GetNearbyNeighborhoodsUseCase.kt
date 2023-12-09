package com.mommydndn.app.domain.usecase.user

import com.mommydndn.app.domain.model.user.NearbyNeighborhoods
import com.mommydndn.app.domain.usecase.UseCase

class GetNearbyNeighborhoodsUseCase : UseCase<GetNearbyNeighborhoodsParams, NearbyNeighborhoods> {
}

data class GetNearbyNeighborhoodsParams(
    val latitude: Double,
    val longitude: Double,
)